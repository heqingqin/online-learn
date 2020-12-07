<template>

  <div class="app-container">

    <h2 style="text-align: center;">添加贴片</h2>

    <el-form label-width="120px">

      <el-form-item label="课程标题">
        <el-input v-model="courseInfo.title" placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"/>
      </el-form-item>

      <!-- 所属分类 TODO -->
      <!-- 一级分类 -->
      <el-form-item label="课程分类">
        <el-select
          v-model="courseInfo.subjectParentId"
          placeholder="一级分类" @change="subjectLevelOneChanged">
          <el-option
            v-for="subject in subjectOneList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"/>
        </el-select>
        <!-- 二级分类 -->
        <el-select v-model="courseInfo.subjectId"
                   placeholder="二级分类">
          <el-option
            v-for="subject in subjectTwoList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"/>
        </el-select>
      </el-form-item>
      <!-- 课程讲师 TODO -->
      <!-- 所属分类：级联下拉列表 -->
      <!-- 一级分类 -->
      <el-form-item label="课程讲师">
        <el-select
          v-model="courseInfo.teacherId"
          placeholder="请选择">
          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"/>
        </el-select>
      </el-form-item>

      <el-form-item label="总课时">
        <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-position="right" placeholder="请填写课程的总课时数"/>
      </el-form-item>

      <!-- 课程简介-->
      <!--        富文本-->
      <el-form-item label="课程简介">
        <tinymce :height="300" v-model="courseInfo.description"/>
      </el-form-item>
      <!-- 课程封面 TODO -->
      <!-- 课程封面-->
      <el-form-item label="课程封面">

        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :action="BASE_API+'/eduoss/fileoss'"
          class="avatar-uploader">
          <img :src="courseInfo.cover">
        </el-upload>

      </el-form-item>
      <el-form-item label="课程价格">
        <el-input-number :min="0" v-model="courseInfo.price" controls-position="right" placeholder="免费课程请设置为0元"/> 元
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">{{ addOrUpdate }}</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>
import course from '@/api/edu/course'
import subject from '@/api/edu/subject'
import Tinymce from '@/components/Tinymce'
export default {
  //声明富文本组件
  components: { Tinymce },
  data() {
    return {
      BASE_API: process.env.BASE_API, // 接口API地址
      teacherList:[],
      subjectOneList:[],//一级分类
      subjectTwoList:[],//二级分类
      addOrUpdate:"保存并下一步",
      courseId:'',
      saveBtnDisabled: false, // 保存按钮是否禁用
      courseInfo:{
        title: '',
        subjectId: '',//二级分类
        subjectParentId: '',//一级分类
        teacherId: '',
        lessonNum: 0,
        description: '',
        cover: '/static/01.jpg',
        price: 0
      }
    }
  },
  created() {
    this.init()
  },
  watch:{//监听
    $route(to,from){//当路由发生变化时执行
      this.init()
    }
  },
  methods: {
    init(){
      if (this.$route.params && this.$route.params.id) {
        this.courseId = this.$route.params.id
        //回显
        this.getCourseInfo()
        this.addOrUpdate="修改并下一步"
      }else{
        this.addOrUpdate="保存并下一步"
        this.courseInfo={
          title: '',
          subjectId: '',//二级分类
          subjectParentId: '',//一级分类
          teacherId: '',
          lessonNum: 0,
          description: '',
          cover: '/static/01.jpg',
          price: 0
        }
        this.getTeacherList()
        this.getSubjectOne()//初始化一级分类
      }
    },
    //根据课程id查询信息
    getCourseInfo(){
      course.getCourseId(this.courseId)
        .then(response=>{
          this.courseInfo = response.data.courseInfoVo
          //查询一级和二级分类
          subject.getAllSubject()
            .then(response=>{
              this.subjectOneList=response.data.list
              //把所有的一级分类进行遍历
              for (let i = 0; i < this.subjectOneList.length; i++) {
                var oneSubject = this.subjectOneList[i]
                if (this.courseInfo.subjectParentId === oneSubject.id) {
                  this.subjectTwoList = oneSubject.children
                }
              }
            })
        })
      this.getTeacherList()
    },
    //上传封面成功
    handleAvatarSuccess(res,file){
      this.courseInfo.cover=res.data.url
    },
    //上传封面之前
    beforeAvatarUpload(file){
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    //添加
    addCourse(){
      console.log('next')
      course.addCourseInfo(this.courseInfo)
        .then(response=>{
          // 提示
          this.$message({
            type: 'success',
            message: "添加课程信息成功"
          })
          //跳转到下一个
          this.$router.push({ path: '/course/chapter/'+response.data.courseId })
        })
    },
    updateCourse(){
      course.updataCourseInfo(this.courseInfo)
        .then(response=>{
          // 提示
          this.$message({
            type: 'success',
            message: "修改课程信息成功"
          })
          //跳转到下一个
          this.$router.push({ path: '/course/chapter/'+this.courseId})
        })
    },
    saveOrUpdate() {
      if (this.courseInfo.id){
        // 有id值修改
        this.updateCourse()
      }else this.addCourse() //没有id值添加
    },
    //查询所有讲师
    getTeacherList(){
      course.getTeacherList()
        .then(response=>{
          this.teacherList=response.data.items
        })
    },
    //查询所有一级分类
    getSubjectOne(){
      subject.getAllSubject()
        .then(response=>{
          this.subjectOneList=response.data.list
        })
    },
    //选中一级分类时,查询所有二级分类
    subjectLevelOneChanged(value){
      for (let i = 0; i < this.subjectOneList.length; i++) {
        if (this.subjectOneList[i].id === value) {
          this.subjectTwoList = this.subjectOneList[i].children
          //把二级分类的id清空
          this.courseInfo.subjectId = ''
        }
      }
    }
  }
}
</script>

