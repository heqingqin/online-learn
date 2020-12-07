<template>
  <div class="app-container">
    <div>讲师添加</div>
    <hr>
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name"/>
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number v-model="teacher.sort" controls-position="right" min="0"/>
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <!--
            数据类型一定要和取出的json中的一致，否则没法回填
            因此，这里value使用动态绑定的值，保证其数据类型是number
          -->
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career"/>
      </el-form-item>
      <el-form-item label="讲师简介">
<!--        <tinymce :height="300" v-model="teacher.intro"/>-->
        <el-input v-model="teacher.intro" :rows="10" type="textarea"/>
      </el-form-item>
      <!-- 讲师头像-->
      <el-form-item label="讲师头像">
        <!-- 头衔缩略图 -->
        <pan-thumb :image="teacher.avatar"/>
        <!-- 文件上传按钮 -->
        <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">更换头像
        </el-button>
        <!--
        v-show：是否显示上传组件
        :key：类似于id，如果一个页面多个图片上传控件，可以做区分
        :url：后台上传的url地址
        @close：关闭上传组件
        @crop-upload-success：上传成功后的回调 -->
        <image-cropper
          v-show="imagecropperShow"
          :width="300"
          :height="300"
          :key="imagecropperKey"
          :url="BASE_API+'/eduoss/fileoss'"
          field="file"
          @close="close"
          @crop-upload-success="cropSuccess"/>
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">{{button}}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import Tinymce from '@/components/Tinymce'
import teacher from '@/api/edu/teacher'
import ImageCropper from '@/components/ImageCropper'
import PanThumb from '@/components/PanThumb'
export default {
  components: { ImageCropper, PanThumb,Tinymce },

  data(){
    return{
      teacher:{
        name: '',
        sort: 0,
        level: 1,
        career: '',
        intro: '',
        avatar: ''
      },
      //上传弹框组件是否显示
      imagecropperShow:false,
      //上传组件key值
      imagecropperKey:0,
      //获取BABEL_ENV地址
      BASE_API:process.env.BASE_API,
      saveBtnDisabled: false // 保存按钮是否禁用,
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
  methods:{
    close(){
      // 上传窗口关闭
       this.imagecropperShow=false
      // 刷新初始化
      this.imagecropperKey=this.imagecropperKey+1
    },
    cropSuccess(data){
      // 上传成功
      this.imagecropperShow=false
      this.teacher.avatar=data.url
      // 刷新初始化
      this.imagecropperKey=this.imagecropperKey+1
    },
    init(){
      if (this.$route.params && this.$route.params.id) {
        //执行更新操作
        const id = this.$route.params.id
        this.getInfo(id)
        this.button='更新'
      }else{
        //执行保存操作
        this.teacher={
          name: '',
          sort: 0,
          level: 1,
          career: '',
          intro: '',
          avatar: ''
        }
        this.button='保存'
      }
    },
    getInfo(id){
      teacher.getTeacherInfo(id)
      .then(response=>{
        this.teacher=response.data.teacher
      })
    },
    saveOrUpdate(){
      //判断是修改还是添加
      if (this.teacher.id) {
        //更新
        this.updateTeacher()
      }else{
        //保存
        this.saveTeacher()
      }
    },
    saveTeacher(){
      teacher.addTeacher(this.teacher)
      .then(response=>{
        this.$message({
          type: 'success',
          message: '添加成功!'
        });
        //回到列表页面
        this.$router.push({ path: '/teacher/table' })
      })
    },
    updateTeacher(){
      teacher.updateTeacher(this.teacher)
      .then(response=>{
        this.$message({
          type: 'success',
          message: '修改成功!'
        });
        //回到列表页面
        this.$router.push({ path: '/teacher/table' })
      })
    }
  }
}
</script>
