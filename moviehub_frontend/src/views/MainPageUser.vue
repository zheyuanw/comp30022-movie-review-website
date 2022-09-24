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
      <el-popover
    :width="50"
    trigger="hover"
    
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
          </el-header>
          <el-main>
            <div class="recommend">Recommend movies</div>
            <el-button type="warning" round class="LRbutton" @click="$router.push('/moviehub/dashboard/post/1234')">Add Post</el-button>  
            <el-divider />
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
.logoutbutton{
  margin-bottom:10px;
  margin-left: 45px;
}
      .header{
      background-color: black;
    
      }

      .LRbutton{
        position:relative;
        left:580px;
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
  position:relative;
  top:-36.5px;
  right:-600px
}

    </style>