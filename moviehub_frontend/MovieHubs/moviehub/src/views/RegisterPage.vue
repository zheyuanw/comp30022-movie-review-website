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
    <el-form-item class = "languagecolour" label="Password" prop="checkPass">
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
   
    <el-form-item>
      <el-button type="primary" @click="submitForm(ruleFormRef)"
        >Sign up</el-button
      >
      <el-button @click="resetForm(ruleFormRef)">Reset</el-button>
    </el-form-item>
  </el-form>


  <div class="hub">
<span contenteditable="true">Movie</span>
<span contenteditable="true">Hub</span>
</div>


</template>

<script lang="ts" setup>
import { reactive, ref } from 'vue'
import type { FormInstance } from 'element-plus'

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

const ruleForm = reactive({
  pass: '',
  checkPass: '',
  confirmPass: ''
})

const rules = reactive({
  pass: [{ validator: validatePass, trigger: 'blur' }],
  checkPass: [{ validator: validatePass2, trigger: 'blur' }],
  confirmPass: [{ validator: validatePass3, trigger: 'blur' }]

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
.inputform {
  width: 50%;
}
.demo-ruleForm {
 text-align:left; border-radius: 8px;margin: 0 auto;width:50%;
  position:fixed;top:220px;left:325px;font-weight: bold;
}
.hub {
  display: inline;
  font-family: sans-serif;
  font-weight: bold;
  font-size: 3vw;
  position: fixed;
  top:35px;
  left: 10%;
  transform: translate(-50%, -50%);

  }

  .hub span:nth-child(1) {
color: white;

}
.hub span:nth-child(2) {
background: #FF9900;
color: black;
border-radius: 1vw;
padding: 0 1vw 1vw 1vw;
display: inline-block;
}
body {
background-image:url('https://scontent.fmel5-1.fna.fbcdn.net/v/t39.30808-6/306272199_1256917685131734_3537777224371844189_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=8bfeb9&_nc_ohc=cwpl-5Xjt7UAX8eKLBr&_nc_ht=scontent.fmel5-1.fna&oh=00_AT9gjDR6Uo4lsrcF4lTvRGKOiaiSau53dI3mdJdh_gRWGw&oe=6329FC9B');
margin: 0;
min-height: 100%;
height: 100%;
position: relative;
}


  </style>