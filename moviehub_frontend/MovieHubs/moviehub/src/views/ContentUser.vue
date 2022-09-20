<template>

  <div class="common-layout">
      <el-container>
        <el-header class="header">
         
    <div class="avatar">
    <el-avatar
      src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
    />
  </div>
        </el-header>
        <el-main>
        
        </el-main>
        
      </el-container>
      
    </div>
    <HubIcon/>
  </template>
  <script lang="ts" setup>
    import { ref } from 'vue'
    import { Search } from '@element-plus/icons-vue'
import HubIcon from '@/components/HubIcon.vue';
    const input1 = ref('')
    const value = ref('')
    const value2=ref('')
  
   
    </script>
  <style scoped>
      .demo-type {
display: flex;
}
.demo-type > div {
flex: 1;
text-align: center;
}

.demo-type > div:not(:last-child) {
border-right: 1px solid var(--el-border-color);
}
    .header{
    background-color: black;
  
    }
    
  .search{
    width:45%;
    margin-top: 15px;
  }
  .select{
    margin-left:10px;
    width:10%;
  }
  .recommend{
    position:absolute;
    font-size:x-large;
    font-weight:bold;
  }
  .avatar{
position:fixed;
top:11.5px;
right:30px
}

  
  </style>