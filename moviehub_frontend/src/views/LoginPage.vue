<template>
  
  <el-form
    ref="ruleFormRef"
    :model="ruleForm"
    status-icon
    :rules="rules"
    label-width="210px"
    class="demo-ruleForm"
    :cell-style="{ 'text-align': 'center'}"
  >

    <el-form-item class = "languagecolour" label="Email" prop="pass">
      <el-input class = "inputform" v-model="ruleForm.pass" type="email" autocomplete="off"/>
    </el-form-item>
    <el-form-item class = "languagecolour" label="Password" prop="checkPass">
      <el-input
        class = "inputform"
        v-model="ruleForm.checkPass"
        type="password"
        autocomplete="off"
      />
      <a href="http://localhost:8080/#/moviehub/forgetpassword" class="forgetpass">Forget password? Click here</a>
    </el-form-item>
    <a href="http://localhost:8080/#/moviehub/registerpage" class="registerlink">If you dont have an account, register here</a>
    <el-form-item class="loginresetbutton">
      <el-button type="primary" @click="submitForm(ruleFormRef)"
        >Log in</el-button
      >
      <el-button @click="resetForm(ruleFormRef)" >Reset</el-button>
    </el-form-item>
  </el-form>

  <HubIcon/>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormInstance } from 'element-plus'
import HubIcon from '@/components/HubIcon.vue';

const ruleFormRef = ref<FormInstance>()

const validatePass = (rule: any, value: any, callback: any) => {
  const reg = /^([A-Za-z0-9]+[.-_])*[A-Za-z0-9]+@[A-Za-z0-9-]+(\.[A-Z|a-z]{2,})+$/;
  if (!value) {
    return callback(new Error('cannot input empty email!'))
  } else if (!reg.test(value)) {
    return callback(new Error('please input valid email!'))
  } else {
    callback()
  }

}
const validatePass2 = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('Please input the password'))
  } else {
    if (value!='' && ((ruleForm.checkPass.length < 8 || ruleForm.checkPass.length > 16))){
      console.log("密码格式错误")
      callback(new Error('Please input correct form of password'))
    }
  }
}

const ruleForm = reactive({
  pass: '',
  checkPass: '',
})

const rules = reactive({
  pass: [{ validator: validatePass, trigger: 'blur' }],
  checkPass: [{ validator: validatePass2, trigger: 'blur' }],

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

const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}
</script>

<style >
.languagecolour .el-form-item__label {
  color: #FF9900;
  
}
.loginresetbutton{
  margin:10px;
}
.registerlink{
  color: rgba(75, 75, 243, 0.767);
  text-decoration:none;
  margin-left: 150px ;
}
.forgetpass{
  color:#FF9900;
  text-decoration:none;
  margin-left:10px
}
.inputform {
  width: 50%;
}
.demo-ruleForm {
 text-align:left; border-radius: 8px;margin: 0 auto;width:50%;
  position:fixed;top:220px;left:325px;font-weight: bold;
}

body {
background-image:url('https://wallpapercave.com/dwp2x/wp11089675.jpg');
}


  </style>