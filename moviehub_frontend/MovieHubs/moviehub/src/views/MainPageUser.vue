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
      <div class="avatar">
      <el-avatar
        src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
      />
    </div>
          </el-header>
          <el-main>
            <div class="recommend">Recommend movies</div>
            <el-button type="warning" round class="LRbutton">Add Post</el-button>  
            <el-divider />
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
      .hub {
      display: inline;
      font-family: sans-serif;
      font-weight: bold;
      font-size: 3vw;
      position: fixed;
      top:25px;
      left: 7%;
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
    .recommend{
      position:absolute;
      font-size:x-large;
      font-weight:bold;
    }
    .avatar{
  position:fixed;
  top:16px;
  right:35px
}

    
    </style>