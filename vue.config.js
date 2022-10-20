const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    proxy: {
      '/api': {
        target: 'https://moivehub-itproject-team004.herokuapp.com',
        allowedHosts: "all",
        changeOrigin: true,
        // pathRewrite: {
        //   '^api': ''
        // }
      }
    }
  }
})
