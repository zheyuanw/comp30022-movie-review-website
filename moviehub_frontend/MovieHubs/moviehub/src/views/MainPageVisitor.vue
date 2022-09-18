<template>

<div class="common-layout">
    <el-container>
      <el-header class="header">
        <el-input
      v-model="input1"
      class="search"
      placeholder="Please Input"
      :suffix-icon="Search"
    />
    <el-select v-model="value2" class="select" placeholder="sortBy">
    <el-option
      v-for="item in optionsSort"
      :key="item.value"
      :label="item.label"
      :value="item.value"
      
    />
  </el-select>
  <el-select v-model="value" class="select" placeholder="filter">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value"
      
    />
  </el-select>
  
    <el-button type="warning" round class="LRbutton">Login/Register</el-button>  
      </el-header>
      <el-main>
        <div class="recommend">Recommend movies</div>

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
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const options = [
  {
    value: 'Drama',
    label: 'Drama',
  },
  {
    value: 'Comedy',
    label: 'Comedy',
  },
  {
    value: 'Horror',
    label: 'Horror',
  },
  {
    value: 'Thriller',
    label: 'Thriller',
  },
  {
    value: 'Action',
    label: 'Action',
  },
  {
    value: 'Sci-fi',
    label: 'Sci-fi',
  },
  {
    value: 'Crime',
    label: 'Crime',
  },
  {
    value: 'Adventure',
    label: 'Adventure',
  },
  {
    value: 'Other',
    label: 'Other',
  },
 ]
 const optionsSort = [
  {
    value: 'rating',
    label: 'rating',
  },
  {
    value: 'Name',
    label: 'Name',
  },
  
 ]  
 
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