<template>
  <div class="app-container">
    <h2 style="text-align: center;">发布新课程</h2>
    <el-steps :active="1" process-status="wait" align-center style="marginbottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="提交审核"/>
    </el-steps>
    <el-form label-width="120px">
      <el-form-item label="课程标题">
        <el-input v-model="courseInfo.title" placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"/>
      </el-form-item>
      <!-- 所属分类 : 级联下拉列表 -->
      <!--一级分类-->
      <el-form-item label="课程分类">
        <el-select @change="subjectLevelOneChanged"
          v-model="courseInfo.subjectParentId" placeholder="请选择">
          <el-option
            v-for="subject in subjectNestedList" :key="subject.id" :label="subject.title" :value="subject.id" />
        </el-select>
        <!--二级分类-->
        <el-select
          v-model="courseInfo.subjectId" placeholder="请选择">
          <el-option
            v-for="subject in subSubjectList" :key="subject.id" :label="subject.title" :value="subject.id" />
        </el-select>
      </el-form-item>
      <!-- 课程讲师 -->
      <el-form-item label="课程讲师">
        <el-select
          v-model="courseInfo.teacherId" placeholder="请选择">
          <el-option
            v-for="teacher in teacherList" :key="teacher.id" :label="teacher.name" :value="teacher.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="总课时">
        <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-position="right" placeholder="请填写课程的总课时数"/>
      </el-form-item>
      <!-- 课程简介 -->
      <el-form-item label="课程简介">
        <tinymce :height="300" v-model="courseInfo.description" />
      </el-form-item>
      <!-- 课程封面 -->
      <el-form-item label="课程封面">
        <el-upload
          :show-file-list="false"
          :on-success="handleCoverSuccess"
          :before-upload="beforeCoverUpload"
          :action="BASE_API+'/oss/file/upload?host=cover'"
          class="avatar-uploader">
          <img :src="courseInfo.cover" alt="课程封面">
        </el-upload>
      </el-form-item>
      <el-form-item label="课程价格">
        <el-input-number :min="0" v-model="courseInfo.price" controls-position="right" placeholder="免费课程请设置为0元"/> 元
      </el-form-item>
      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="next">保存并下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
  import course from '@/api/edu/course'
  import subject from '@/api/edu/subject'
  import teacher from '@/api/edu/teacher'
  import Tinymce from '@/components/Tinymce'

  const defaultForm = {
    title: '',
    subjectId: '',
    subjectParentId: '',
    teacherId: '',
    lessonNum: '',
    description: '',
    cover: process.env.VUE_APP_OSS_PATH + '/cover/Programming.jpg',
    price: 0
  }

  export default {
    components: { Tinymce },
    data() {
      return {
        courseInfo: defaultForm,
        saveBtnDisabled: false, // 保存按钮是否禁用
        subSubjectList: [],
        subjectNestedList: [],
        teacherList: [],
        BASE_API: process.env.VUE_APP_BASE_API
      }
    },

    watch: {
      $route(to, from) {
        this.init()
      }
    },

    created() {
      this.init()
    },

    methods: {
      init() {
        // 填充 subject 下拉列表
        this.initSubjectList()
        // 填充 teacher 下拉列表
        this.initTeacherList()
        // 有参数说明是 update, 需要先 fetch
        if (this.$route.params && this.$route.params.id) {
          const id = this.$route.params.id
          // 根据 id 获取课程基本信息
          this.fetchCourseInfo(id)
        } else {
          this.courseInfo = { ...defaultForm }
        }
      },

      initTeacherList() {
        teacher.getList().then(response => {
          this.teacherList = response.data.items
        })
      },

      initSubjectList() {
        subject.getSubjectTree().then(response => {
          this.subjectNestedList = response.data.items
        })
      },

      fetchCourseInfo(id) {

        course.getCourseInfo(id).then(response => {
          this.courseInfo = response.data.item
          // 初始化二级分类列表
          for (let key in this.subjectNestedList) {
            if(this.courseInfo.subjectParentId === this.subjectNestedList[key].id) {
              this.subSubjectList = this.subjectNestedList[key].children
            }
          }
        }).catch(response => {
          this.$message({
            type: 'error',
            message: response.message
          })
        })
      },

      next() {
        this.saveBtnDisabled = true
        if (!this.courseInfo.id) {
          this.saveData()
        } else {
          this.updateData()
        }
      },

      saveData() {
        course.saveCourseInfo(this.courseInfo).then(response => {
          this.$message({
            type: 'success',
            message: '保存成功!'
          })
          return response // 将响应结果传递给 then
        }).then(response => {
          this.$router.push({ path: '/edu/course/chapter/' + response.data.courseId })
        }).catch(response => {
          this.$message({
            type: 'error',
            message: response.message
          })
        })
      },

      updateData() {
        this.saveBtnDisabled = true
        course.updateCourseInfo(this.courseInfo).then(response => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          return response
        }).then(response => {
          this.$router.push({ path: '/edu/course/chapter/' + response.data.courseId })
        }).catch(response => {
          this.$message({
            type: 'error',
            message: response.message
          })
        })
      },

      subjectLevelOneChanged(value) {
        for (let key in this.subjectNestedList) {
          if (value === this.subjectNestedList[key].id) {
            this.subSubjectList = this.subjectNestedList[key].children
            this.courseInfo.subjectId = ''
          }
        }
      },

      handleCoverSuccess(res, file) {
        this.courseInfo.cover = res.data.url
      },

      beforeCoverUpload(file) {
        const isJPG = file.type === 'image/jpeg'
        const isLt2M = file.size / 1024 / 1024 < 2

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!')
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片不能超过 2MB!')
        }
        return isJPG && isLt2M
      }
    }
  }
</script>
<style scoped>
  .tinymce-container {
    line-height: 29px;
  }
</style>
