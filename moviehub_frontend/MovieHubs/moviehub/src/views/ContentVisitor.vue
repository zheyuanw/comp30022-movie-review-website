<template>

  <div class="common-layout">
      <el-container>
        <el-header class="header">
          
    
      <el-button type="warning" round class="LRbutton">Login/Register</el-button>  
        </el-header>
        <el-main>
          
  
        </el-main>
        
      </el-container>
      
    </div>
    <div class="hub">
  <span contenteditable="true">Movie</span>
  <span contenteditable="true">Hub</span>
  </div>
  </template>
  <script lang="ts" setup>
    import { ref } from 'vue'
    import { Search } from '@element-plus/icons-vue'
    const input1 = ref('')
    const value = ref('')
    const value2=ref('')
  
   
    </script>
  <style>
    .header{
    background-color: black;
  
    }
    .hub {
    display: inline;
    font-family: sans-serif;
    font-weight: bold;
    font-size: 3vw;
    position: fixed;
    top:25px;
    left: 5%;
    transform: translate(-50%, -50%);
  
    }
  
    .hub span:nth-child(1) {
  color: white;
  font-size:large;
  
  }
  .hub span:nth-child(2) {
  background: #FF9900;
  color: black;
  border-radius: 1vw;
  padding: 0 1vw 1vw 1vw;
  display: inline-block;
  font-size:large;
  }
  body {
  margin: 0;
  min-height: 100%;
  height: 100%;
  position: relative;
  }
  .search{
    width:45%;
    margin-top: 15px;
  }
  .select{
    margin-left:10px;
    width:10%;
  }
  .LRbutton{
    position:fixed;
    top:12px;
    right:35px
  }
  .recommend{
    position:absolute;
    font-size:x-large;
    font-weight:bold;
  }
  
  </style>