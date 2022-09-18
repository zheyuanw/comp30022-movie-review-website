<template>
 <div class="hub">
<span contenteditable="true">Movie</span>
<span contenteditable="true">Hub</span>
</div>
</template>

<style >
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
