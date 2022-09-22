
<template>

  <div class="common-layout">
      <el-container>
        <el-header class="header" >
          
          <div class="avatar">
        <el-avatar
        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
          /><!--头像-->
        </div>
        <el-link>
          <el-button type="primary" class="homeicon">Dashboard</el-button>
        </el-link>
        <el-link>
          <el-button type="primary" class="home">Home</el-button>
        </el-link>
        </el-header>
        
        <el-form class="allform"
        ref="ruleFormRef"
        :rules="rules"
        status-icon
        >
          
        <el-form-item label = "Username: " class="movietitle">
          <el-input class="movietitleinput" style = "width: 300px" v-model="input" placeholder="Please input new username" />
        </el-form-item>
        <el-form-item label="Gender: " class = "languagecolour">
          <el-select class="genderoption" style = "width: 300px" v-model="value" placeholder="Select" size="large">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item class = "languagecolour" label="Age">
    <el-input-number class="ageoption" style = "width: 300px" v-model="age" :min="1" :max="120" @change="handleChange" />
  </el-form-item>
  <el-form-item class = "languagecolour" label="New Password" prop="checkPass">
      <el-input
        class = "inputform"
        v-model="ruleForm.checkPass"
        type="password"
        autocomplete="off"
      />
    </el-form-item>
    <el-form-item class = "languagecolour" label="confirm password" prop="confirmPass">
      <el-input  class = "inputform" v-model="ruleForm.confirmPass" type="Password" autocomplete="off"/>
    </el-form-item>
      <el-form-item label="Add avatar:" class="labelcolor">
        <el-upload
    v-model:file-list="fileList"
    class="upload-demo"
    action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
    multiple
    :on-preview="handlePreview"
    :on-remove="handleRemove"
    :before-remove="beforeRemove"
    :limit="1"
    :on-exceed="handleExceed"
    :auto-upload="false"
    
  >
    <el-button type="primary">Click to upload</el-button>
    <template #tip>
      <div class="el-upload__tip">
        jpg/png files with a size less than 500KB.
      </div>
    </template>
  </el-upload>
      </el-form-item> 
      
      
    </el-form>
    <el-button type="warning" round class="LRbutton">Save</el-button> 
  </el-container>
      
    </div>
    
   <HubIcon/>
  </template>


  <script lang="ts" setup>
    import { ElMessage, ElMessageBox } from 'element-plus'
    import { reactive, ref } from 'vue'
import type { FormInstance } from 'element-plus'
import type { UploadProps, UploadUserFile } from 'element-plus'
import HubIcon from '@/components/HubIcon.vue';
const ruleFormRef = ref<FormInstance>()
const input = ref('')
const textarea = ref('')
const value = ref()
const age = ref(1)

const fileList = ref<UploadUserFile[]>([

])
const handleChange = (value: number) => {
  console.log(value)
}
const validatePass2 = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('Please input the password'))
  } else {
    if (value!='' && ((ruleForm.checkPass.length < 8 || ruleForm.checkPass.length > 16))){
      console.log("密码格式错误")
      callback(new Error('Please input correct form of password'))
    }
    if (ruleForm.confirmPass !== '') {
      if (!ruleFormRef.value) return
      ruleFormRef.value.validateField('confirmPass', () => null)
    }
    callback()
  }
}
const validatePass3 = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('Please input the password again'))
  } else if (value !== ruleForm.checkPass) {
    callback(new Error("Two inputs don't match!"))
  } else {
    callback()
  }
}
const rules = reactive({

  checkPass: [{ validator: validatePass2, trigger: 'blur' }],
  confirmPass: [{ validator: validatePass3, rigger: 'blur' }],
  
})

const ruleForm = reactive({
  
  checkPass: '',
  confirmPass: '',
  
})
const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      console.log('submit!')
    } else {
      console.log('error submit!')
      return false
    }
  })
}
const options = [
  {
    value: '0',
    label: 'Male',

  },
  {
    value: '1',
    label: 'Female',
  },
  
]
const handleRemove: UploadProps['onRemove'] = (file, uploadFiles) => {
  console.log(file, uploadFiles)
}

const handlePreview: UploadProps['onPreview'] = (uploadFile) => {
  console.log(uploadFile)
}

const handleExceed: UploadProps['onExceed'] = (files, uploadFiles) => {
  ElMessage.warning(
    `The limit is 1, you selected ${files.length} files this time, add up to ${
      files.length + uploadFiles.length
    } totally`
  )
}

const beforeRemove: UploadProps['beforeRemove'] = (uploadFile, uploadFiles) => {
  return ElMessageBox.confirm(
    `Cancel the transfert of ${uploadFile.name} ?`
  ).then(
    () => true,
    () => false
  )
}

    </script>

<style>
  .languagecolour {
  color: #FF9900;
  margin-left:330px;
  margin-top:40px;
}
  body {
background-color: #222231;
}
  .header{
  background-color: black;

  }
.avatar{
  position:relative;
  top:11.5px;
  right:-600px
}
.LRbutton{
  width:10%;
  margin-left:590px;
}
.main{
  background-color: white;
  height:64px;
  
}
.upload-demo{
  margin-left:-50px;
}
.posthere{
  position: absolute;
  font-weight: bold;
  font-size: x-large;
}
.movietitle{
   margin-top:50px;
   margin-left:330px;

}
.labelcolor{
   margin-top:50px;
   margin-left:330px;

}
.textinput{
  margin-left:17px;
  width:50%;
 
}
.star{
  margin-left:24px;
}

.movietitle .el-form-item__label {
  color: orange
}

.labelcolor .el-form-item__label {
  color: orange
}
.languagecolour .el-form-item__label {
  color: orange
}

.movietitleinput{
  width:50%;
}
.homeicon{
  width:150%;
  position:relative;
  top:-30px;
  left:-400px;

  
}
.home{
  width:150%;
  position:relative;
  top:-30px;
  left:-380px;

  
}
.genderoption{
  margin-left:17px;
}
.homelink {
  text-decoration: none;
}
.ageoption{
  margin-left: 40px;
}
.allform{
  margin-left:100px;
}
.inputform{
  width:50%;
  margin-left: 1px;
}
</style>