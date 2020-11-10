<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="searchObj.name" placeholder="教师名称" />
      </el-form-item>
      <el-form-item>
        <el-select v-model="searchObj.level" placeholder="头衔">
          <el-option label="高级讲师" :value="1" />
          <el-option label="首席讲师" :value="2" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-form-item>
          <el-date-picker
            v-model="searchObj.begin"
            type="date"
            placeholder="查询开始时间"
            value-format="yyyy-MM-dd"
            style="width: 100%"
          />
        </el-form-item>
        <el-form-item>
          <el-date-picker
            v-model="searchObj.end"
            placeholder="查询结束时间"
            value-format="yyyy-MM-dd"
            style="width: 100%"
          />
        </el-form-item>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="fetchData">查询</el-button>
        <el-button type="default" @click="resetData">清空</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="list"
      style="width: 100%"
      :default-sort="{ prop: 'date', order: 'descending' }"
    >
      <el-table-column label="ID" type="index" :index="indexMethod" sortable width="180" />
      <el-table-column prop="avatar" label="头像" sortable width="180">
        <template slot-scope="scope">
          <el-avatar shape="square" :size="30" :src="scope.row.avatar" />
        </template>
      </el-table-column>
      />
      <el-table-column prop="name" label="姓名" sortable width="180" />
      <el-table-column prop="career" label="职位" sortable width="180" />

      <el-table-column prop="gmtCreate" label="创建时间" />
      <el-table-column
        fixed="right"
        label="操作"
        width="120"
      >
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click="deleteRow(scope.row.id)"
          >
            移除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      layout="total, sizes, prev, pager, next, jumper"
      :page-sizes="[1, 5, 10, 20]"
      @current-change="pageChange"
      @size-change="sizeChange"
    />
  </div>
</template>

<script>
import { getPageList } from '@/api/edu/teacher'
import { deleteById } from '@/api/edu/teacher'

export default {
  data() {
    return {
      list: null,
      listLoading: true,
      page: 1,
      limit: 5,
      total: 0,
      searchObj: {}
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getPageList(this.page, this.limit, this.searchObj).then((response) => {
        this.list = response.data.rows
        this.total = response.data.total
        this.listLoading = false
        console.log(this.list)
      })
    },
    // 当前页变更
    pageChange(page = 1) {
      this.page = page
      this.fetchData()
    },
    // pageSize 变更
    sizeChange(limit = 1) {
      this.limit = limit
      this.fetchData()
    },
    formatterLevel(row, column) {
      return row.level === 1 ? '高级讲师' : '首席讲师'
    },

    // 清空查询条件
    resetData() {
      this.searchObj = {}
      this.fetchData()
    },

    // 列序号
    indexMethod(index) {
      return (this.page - 1) * this.limit + index + 1
    },

    // 删除讲师
    deleteRow(index) {
      // this.$message(index)
      deleteById(index).then((response) => {
        this.fetchData()
      })
    }

  }
}
</script>
