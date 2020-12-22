<template>
  <div class="app-container">
    <div v-if="this.$route.params && this.$route.params.id"><h2 style="text-align: center;">修改课程信息</h2></div>
    <div v-else><h2 style="text-align: center;">发布新课程</h2></div>

    <el-steps :active="2" process-status="wait" align-center style="marginbottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="提交审核"/>
    </el-steps>

    <el-button type="text" @click="dialogChapterFormVisible = true">添加章节</el-button>
    <!-- 添加和修改章节表单 -->
    <el-dialog :visible.sync="dialogChapterFormVisible" title="添加章节">
      <el-form :model="chapter" label-width="120px">
        <el-form-item label="章节标题">
          <el-input v-model="chapter.title"/>
        </el-form-item>
        <el-form-item label="章节排序">
          <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 章节 -->
    <ul class="chapterList">
      <li v-for="chapter in chapterNestedList" :key="chapter.id">
        <p>
          {{ chapter.title }}

          <span class="acts">
            <el-button type="text" @click="addChapter(chapter.id)">添加课时</el-button>
            <el-button type="text" @click="editChapter(chapter.id)">编辑</el-button>
            <el-button type="text" @click="removeChapter(chapter.id)">删除</el-button>
          </span>
        </p>

        <ul class="chapterList videoList">
          <li
            v-for="video in chapter.children"
            :key="video.id">
            <p>{{ video.title }}
              <span class="acts">
                <el-button type="text" @click="editVideo(video.id)">编辑</el-button>
                <el-button type="text" @click="removeVideo(video.id)">删除</el-button>
              </span>
            </p>
          </li>
        </ul>
      </li>
    </ul>
    <!-- 视频 -->

    <!-- 添加和修改课时表单 -->
    <el-dialog :visible.sync="dialogVideoFormVisible" title="添加课时">
      <el-form :model="video" label-width="120px">
        <el-form-item label="课时标题">
          <el-input v-model="video.title"/>
        </el-form-item>
        <el-form-item label="课时排序">
          <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
        </el-form-item>
        <el-form-item label="是否免费">
          <el-radio-group v-model="video.free">
            <el-radio :label="true">免费</el-radio>
            <el-radio :label="false">默认</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上传视频">
          <el-upload
            :on-success="handleVodUploadSuccess"
            :on-remove="handleVodRemove"
            :before-remove="beforeVodRemove"
            :on-exceed="handleUploadExceed"
            :file-list="fileList"
            :action="BASE_API+'/vod/video/upload'"
            :limit="1"
            class="upload-demo">
            <el-button size="small" type="primary">上传视频</el-button>
            <el-tooltip placement="right-end">
              <div slot="content">最大支持1G，<br>
                支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br>
                GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br>
                MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br>
                SWF、TS、VOB、WMV、WEBM 等视频格式上传
              </div>
              <em class="el-icon-question"/>
            </el-tooltip>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
        <el-button :disabled="saveVideoBtnDisabled" type="primary" @click="saveOrUpdateVideo">确 定</el-button>
      </div>
    </el-dialog>

    <div>
      <el-button @click="previous">上一步</el-button>
      <el-button :disabled="saveBtnDisabled" type="primary" @click="next">下一步</el-button>
    </div>
  </div>
</template>
<script>
  import chapter from '@/api/edu/chapter'
  import video from '@/api/edu/video'
  import vod from '@/api/edu/vod'

  export default {
    data() {
      return {
        saveBtnDisabled: false, // 保存按钮是否禁用
        courseId: '',
        chapterNestedList: [],
        dialogChapterFormVisible: false,
        chapter: {
          title: '',
          sort: 0
        },
        saveVideoBtnDisabled: false, // 课时按钮是否禁用
        dialogVideoFormVisible: false,  // 是否显示课时表单
        chapterId: '',
        video: {
          title: '',
          sort: 0,
          free: 0,
          videoSourceId: '',
          videoOriginalName: ''
        },
        fileList: [],
        BASE_API: process.env.VUE_APP_BASE_API
      }
    },
    created() {
      console.log('chapter created')
      this.init()
    },
    methods: {
      init() {
        if (this.$route.params && this.$route.params.id) {
          this.courseId = this.$route.params.id
          this.fetchChapterNestedListByCourseId(this.courseId)
        }
      },
      fetchChapterNestedListByCourseId(courseId) {
        chapter.getNestedTreeList(courseId).then(response => {
          this.chapterNestedList = response.data.items
        })
      },
      previous() {
        // if (this.$route.params && this.$route.params.id) {
        //   this.$router.push({ path: '/edu/course/info/' + this.$route.params.id })
        // }
        console.log('previous')
        this.$router.push({path: '/edu/course/info/' + this.courseId})
      },
      next() {
        console.log('next')
        this.$router.push({path: '/edu/course/publish/' + this.$route.params.id})
      },
      saveOrUpdate() {
        this.saveBtnDisabled = true
        if (!this.chapter.id) {
          this.saveData()
        } else {
          this.updateData()
        }
      },
      saveData() {
        this.chapter.courseId = this.courseId
        chapter.save(this.chapter)
          .then(response => {
            this.$message({
              type: 'success',
              message: '保存成功!'
            })
            this.helpSave()
          }).catch(reason => {
          this.$message({
            type: 'error',
            message: reason.message
          })
        })
      },
      helpSave() {
        this.dialogChapterFormVisible = false // 如果成功则关闭对话框
        this.fetchChapterNestedListByCourseId(this.courseId) // 刷新列表
        this.chapter.title = '' // 重置章节标题
        this.chapter.sort = 0 // 重置章节排序
        this.saveBtnDisabled = false
      },
      addChapter(chapterId) {
        this.dialogVideoFormVisible = true
        this.chapterId = chapterId;
        this.video = {
          title: '',
          sort: 0,
          free: 0,
          videoSourceId: '',
          videoOriginalName: ''
        }
        this.fileList = []
      },
      editChapter(chapterId) {
        this.dialogChapterFormVisible = true
        chapter.getById(chapterId).then(response => {
          this.chapter = response.data.item
        })
      },
      updateData() {
        chapter.updateById(this.chapter).then(response => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          this.helpSave()
        }).catch(response => {
          this.$message({
            type: 'error',
            message: response.message
          })
        })
      },
      removeChapter(chapterId) {
        this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          return chapter.removeById(chapterId)
        }).then(() => {
          this.fetchChapterNestedListByCourseId(this.courseId)
          this.$message({
            type: 'success',
            message: '删除成功'
          })
        }).catch(response => {
          if (response === 'cancel') {
            this.$message({
              type: 'info',
              message: '已取消删除'
            })
          } else {
            this.$message({
              type: 'error',
              message: response.message
            })
          }
        })
      },
      /** *************** Video *****************/
      saveOrUpdateVideo() {
        if (!this.video.id) {
          this.saveDataVideo()
        } else {
          this.updateDataVideo()
        }
      },

      saveDataVideo() {
        this.video.courseId = this.courseId
        this.video.chapterId = this.chapterId
        video.saveVideoInfo(this.video).then(response => {
          this.$message({
            type: 'success',
            message: '保存成功!'
          })
          this.helpSaveVideo()
        })
      },
      editVideo(videoId) {
        this.dialogVideoFormVisible = true
        video.getVideoInfoById(videoId).then(response => {
          this.video = response.data.item
          this.video.videoOriginalName === '' ?
            this.fileList = [] : this.fileList = [{'name': this.video.videoOriginalName}]
        })
      },
      updateDataVideo() {
        video.updateVideoInfoById(this.video).then(response => {
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
          this.helpSaveVideo()
        })
      },
      helpSaveVideo() {
        this.dialogVideoFormVisible = false
        this.fetchChapterNestedListByCourseId(this.courseId)
        this.video.title = ''
        this.video.sort = 0
        this.video.videoSourceId = ''
        this.saveVideoBtnDisabled = false
      },
      removeVideo(videoId) {
        this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          return video.removeById(videoId)
        }).then(() => {
          this.fetchChapterNestedListByCourseId(this.courseId)
          this.$message({
            type: 'success',
            message: '删除成功'
          })
        }).catch(response => {
          if (response === 'cancel') {
            this.$message({
              type: 'info',
              message: '已取消删除'
            })
          } else {
            this.$message({
              type: 'error',
              message: response.message
            })
          }
        })
      },
      handleVodUploadSuccess(response, file, fileList) {
        this.video.videoSourceId = response.data.videoId
        this.video.videoOriginalName = file.name
      },
      handleUploadExceed(files, fileList) {
        this.$message.warning('请先删除已上传的视频')
      },
      beforeVodRemove(file, fileList) {
        return this.$confirm(`确定移除 ${file.name} ?`)
      },
      handleVodRemove(file, fileList) {
        vod.removeById(this.video.videoSourceId).then(response => {
          this.video.videoSourceId = ''
          this.video.videoOriginalName = ''
          this.fileList = []
          this.$message({
            type: 'success',
            message: response.message
          })
        })
      }
    }
  }
</script>
<style scoped>
  .chapterList {
    position: relative;
    list-style: none;
    margin: 0;
    padding: 0;
  }

  .chapterList li {
    position: relative;
  }

  .chapterList p {
    float: left;
    font-size: 20px;
    margin: 10px 0;
    padding: 10px;
    height: 70px;
    line-height: 50px;
    width: 100%;
    border: 1px solid #DDD;
  }

  .chapterList .acts {
    float: right;
    font-size: 14px;
  }

  .videoList {
    padding-left: 50px;
  }

  .videoList p {
    float: left;
    font-size: 14px;
    margin: 10px 0;
    padding: 10px;
    height: 50px;
    line-height: 30px;
    width: 100%;
    border: 1px dotted #DDD;
  }

</style>
