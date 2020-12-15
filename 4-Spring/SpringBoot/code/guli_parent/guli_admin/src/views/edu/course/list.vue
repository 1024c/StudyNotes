<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="课程类别">
        <el-select
          v-model="searchObj.subjectParentId"
          placeholder="请选择"
          @change="subjectLevelOneChanged">
          <el-option
            v-for="subject in subjectNestedList"
            :key="subject.id"
            :value="subject.id"
            :label="subject.title" />
        </el-select>
        <el-select
          v-model="searchObj.subjectId"
          placeholder="请选择">
          <el-option
            v-for="subject in subSubjectList"
            :key="subject.id"
            :value="subject.id"
            :label="subject.title" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-input v-model="searchObj.title" placeholder="课程标题"/>
      </el-form-item>
      <el-form-item>
        <el-select
          v-model="searchObj.teacherId"
          placeholder="请选择讲师">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :value="teacher.id"
            :label="teacher.name" />
        </el-select>
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="fetchData()">查询</el-button>
      <el-button type="default" @click="resetData()">重置</el-button>
    </el-form>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="数据加载中..."
      border
      fit
      highlight-current-row
      row-class-name="myClassList">
      <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (page - 1) * limit + scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="课程信息" width="470" align="center">
        <template slot-scope="scope">
          <div class="info">
            <div class="pic">
              <img :src="scope.row.cover" alt="scope.row.title" width="150">
            </div>
            <div class="title">
              <a href="">{{ scope.row.title }}</a>
              <p>{{ scope.row.lessonNum }}</p>
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.gmtCreate.substr(0, 10) }}
        </template>
      </el-table-column>
      <el-table-column label="发布时间" align="center">
        <template slot-scope="scope">
          {{ scope.row.gmtModified.substr(0, 10) }}
        </template>
      </el-table-column>
      <el-table-column label="价格" width="100" align="center">
        <template slot-scope="scope">
          {{ Number(scope.row.price) === 0 ? '免费' : '$' + scope.row.price.toFixed(2) }}
        </template>
      </el-table-column>
      <el-table-column prop="buyCount" label="付费学员" width="100" align="center">
        <template slot-scope="scope">
          {{ scope.row.byCount }} 人
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150" align="center">
        <template slot-scope="scope">
          <router-link :to="'/edu/course/info/' + scope.row.id">
            <el-button type="text" size="mini" icon="el-icon-edit">编辑课程信息</el-button>
          </router-link>
          <router-link :to="'/edu/course/chapter/' + scope.row.id">
            <el-button type="text" size="mini" icon="el-icon-edit">编辑课程大纲</el-button>
          </router-link>
          <el-button type="text" size="mini" icon="el-icon-delete">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center"
      layout="total, prev, pager, next, jumper"
      @current-change="fetchData"/>
  </div>
</template>
<script>
  import course from '@/api/edu/course'
  import teacher from '@/api/edu/teacher'
  import subject from "@/api/edu/subject";

  export default {
    data() {
      return {
        listLoading: true,
        list: null,
        total: 0,
        page: 1,
        limit: 10,
        searchObj: {
          subjectParentId: '',
          subjectId: '',
          title: '',
          teacherId: ''
        },
        teacherList: [],
        subjectNestedList: [],
        subSubjectList: []
      }
    },
    created() {
      this.fetchData();
      this.initSubjectList();
      this.initTeacherList();
    },
    methods: {
      fetchData(page = 1) { // 调用api层获取数据库中的数据
        console.log('加载列表')
        this.page = page
        this.listLoading = true
        course.getPageList(this.page, this.limit, this.searchObj)
          .then(response => {
            if (response.success === true) {
              this.list = response.data.items
              this.total = response.data.total
            }
            this.listLoading = false
          })
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

      subjectLevelOneChanged(value) {
        for (let i = 0; i < this.subjectNestedList.length; i++) {
          if (this.subjectNestedList[i].id === value) {
            this.subSubjectList = this.subjectNestedList[i].children
            this.searchObj.subjectId = ''
          }
        }
      },

      resetData() {
        this.searchObj = {}
        this.subSubjectList = []
        this.fetchData()
      }
    }
  }
</script>
<style scoped>
  .myClassList .info {
    width: 450px;
    overflow: hidden;
  }

  .myClassList .info .pic {
    width: 150px;
    height: 90px;
    overflow: hidden;
    float: left;
  }

  .myClassList .info .pic a {
    display: block;
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
  }

  .myClassList .info .pic img {
    display: block;
    width: 100%;
  }

  .myClassList td .info .title {
    width: 280px;
    float: right;
    height: 90px;
  }

  .myClassList td .info .title a {
    display: block;
    height: 48px;
    line-height: 24px;
    overflow: hidden;
    color: #00baf2;
    margin-bottom: 12px;
  }

  .myClassList td .info .title p {
    line-height: 20px;
    margin-top: 5px;
    color: #818181;
  }
</style>
