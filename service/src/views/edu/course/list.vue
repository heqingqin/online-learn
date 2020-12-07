<template>
  <div class="app-container">
    <div>课程列表</div>
    <hr>
    <template>
      <!--查询表单-->
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item>
          <el-input v-model="courseQuery.title" placeholder="课程名称"/>
        </el-form-item>
        <el-form-item>
          <el-select v-model="courseQuery.status" clearable placeholder="课程状态">
            <el-option :value="'Normal'" label="已发布"/>
            <el-option :value="'Draft'" label="未发布"/>
          </el-select>
        </el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
        <el-button type="default" @click="resetData()">清空</el-button>
      </el-form>
      <!-- 表格 -->
      <el-table
        v-loading="listLoading"
        :data="list"
        element-loading-text="数据加载中"
        border
        fit
        highlight-current-row>
        <el-table-column
          label="序号"
          width="70"
          align="center">
          <template slot-scope="scope">
            {{ (page - 1) * limit + scope.$index + 1 }}
          </template>
        </el-table-column>
        <el-table-column prop="title" label="课程名称" width="180" />
        <el-table-column label="课程状态" width="80">
          <template slot-scope="scope">
            {{ scope.row.status==='Normal'?'已发布':'未发布' }}
          </template>
        </el-table-column>
        <el-table-column label="价格" width="70">
          <template slot-scope="scope">
            {{ scope.row.price===0?'免费':'￥'+scope.row.price }}
          </template>
        </el-table-column>
        <el-table-column prop="lessonNum" label="课时数" width="70"/>
        <el-table-column prop="gmtCreate" label="添加时间" width="180"/>
        <el-table-column prop="viewCount" label="浏览数量" width="90" />
        <el-table-column label="操作" width="460" align="center">
          <template slot-scope="scope">
            <router-link :to="'/course/info/'+scope.row.id">
              <el-button type="primary" size="mini" icon="el-icon-edit">编辑课程的基本信息</el-button>
            </router-link>
            <router-link :to="'/course/chapter/'+scope.row.id">
              <el-button type="primary" size="mini" icon="el-icon-edit">编辑课程的大纲</el-button>
            </router-link>
            <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除课程信息</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>
    <!-- 分页 -->
    <el-pagination
      :current-page="page"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />
  </div>
</template>

<script>
import course from '@/api/edu/course'
export default {
  //写核心代码
  // data: {
  //
  // },
  data(){//定义变量和初始值
    return{
      list:null,//查询之后接口返回的集合
      page:1,//当前页
      limit:8,//每条记录数
      total:0,//查询总数
      courseQuery:{} //条件封装对象
    }
  },
  created() {//页面渲染之前执行，一般调用methods的方法
    this.getList()
  },
  methods:{//创建具体方法，调用teacher.js定义的方法
    getList(page=1){
      this.page=page
      course.getCourseListPage(this.page,this.limit,this.courseQuery)
        .then(response=>{
          this.list = response.data.rows
          this.total = response.data.total
        })
    },
    resetData(){
      this.courseQuery={}
      this.getList()
    },
    removeDataById(id){
      this.$confirm('此操作将永久删除该课程的记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        course.deleteCourse(id)
          .then(response=>{
            //删除成功
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getList()
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  }
}
</script>
