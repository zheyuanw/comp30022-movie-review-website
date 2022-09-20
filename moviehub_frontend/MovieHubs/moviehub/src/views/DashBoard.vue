
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
        
        <el-form 
        >
          
    </el-form>
    
  </el-container>
      
    </div>
    
   <HubIcon/>
  </template>


  <script lang="ts" setup>
    import HubIcon from '@/components/HubIcon.vue';

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
