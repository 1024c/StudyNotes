<template>
  <div class="app-container">
    讲师列表
  </div>
</template>

<script>
  import { getList } from '@/api/edu/teacher'

  export default {
    data() {
      return {
        list: null,
        listLoading: true
      }
    },
    created() {
      this.fetchData()
      console.log(this.list);
    },
    methods: {
      fetchData() {
        this.listLoading = true
        getList().then(response => {
          this.list = response.data.items
          this.listLoading = false
        })
      }
    }
  }
</script>
