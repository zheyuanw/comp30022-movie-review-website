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
      <el-input  class = "inputform" v-model="ruleForm.pass" type="email" autocomplete="off"/>
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
  
    <el-form-item class = "languagecolour"  label="verification code" prop="checkverificationcode">
      <el-input  class = "inputform" v-model="ruleForm.checkverificationcode"  autocomplete="off"/>
      <el-button type="warning" class="getcode">Get verification code</el-button>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm(ruleFormRef);$router.push('/moviehub/loginpage')" 
        >Reset password</el-button
      >

    </el-form-item>
  </el-form>
<HubIcon/>
</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormInstance } from 'element-plus'
import HubIcon from '@/components/HubIcon.vue';

const ruleFormRef = ref<FormInstance>()
  const input = ref('')
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
const validatePass7 = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('Please input the verification code'))
  } else {
    callback()
  }
}
const ruleForm = reactive({
  pass: '',
  checkPass: '',
  confirmPass: '',
  checkverificationcode:''
})

const rules = reactive({
  pass: [{ validator: validatePass, trigger: 'blur' }],
  checkPass: [{ validator: validatePass2, trigger: 'blur' }],
  confirmPass: [{ validator: validatePass3, trigger: 'blur' }],
  checkverificationcode: [{ validator: validatePass3, trigger: 'blur' }]

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
  body {
background-image:url('https://wallpapercave.com/dwp2x/wp11089675.jpg');

}
.languagecolour .el-form-item__label {
  color: #FF9900;
}
.inputform {
  width: 50%;
}
.demo-ruleForm {
 text-align:left; 
 border-radius: 8px;
 margin:0 auto;
 width:50%;
  position:fixed;
  top:150px;
  left:325px;
  font-weight: bold;
}


.getcode{
  margin-left:20px;
}

  </style>