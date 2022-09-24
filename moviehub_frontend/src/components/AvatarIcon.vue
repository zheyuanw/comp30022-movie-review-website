<template>
  <el-popover
    :width="200"
    trigger="hover"
    content=""
    
  >
    <el-button @click="$router.push('/moviehub/loginpage')" class="logoutbutton">logout</el-button>
    <el-button @click="$router.push('/moviehub/changepassword/1234')">change password</el-button>
    <template #reference>
      <div class="avatar" >
        <el-avatar 
        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
        @click="$router.push('/moviehub/dashboard/1234')"
          /><!--头像-->
    </div>
    </template>
  </el-popover>
  
</template>
<script lang="ts" setup>
  
  </script>
<style>
  .avatar{
  position:relative;
  top:11.5px;
  right:-600px
}
.logoutbutton{
  margin-bottom:10px;
  margin-left: 45px;
}

</style>
