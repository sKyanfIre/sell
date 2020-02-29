<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
*{
  box-sizing: border-box;
}
html, body, #app {
  width: 100%;
  height: 100%;
  margin: 0px;
  padding: 0px;
  font-size: 16px;
}
ul,li{
  list-style: none;
  margin: 0px;
  padding: 0px;
}
.button{
  background: #79bbff;
  background-image: -webkit-linear-gradient(top, #79bbff, #378de5);
  background-image: -moz-linear-gradient(top, #79bbff, #378de5);
  background-image: -ms-linear-gradient(top, #79bbff, #378de5);
  background-image: -o-linear-gradient(top, #79bbff, #378de5);
  background-image: linear-gradient(to bottom, #79bbff, #378de5);
  -webkit-border-radius: 5%;
  -moz-border-radius: 5%;
  border-radius: 5px;
  text-shadow: 0px 1px 0px #528ecc;
  -webkit-box-shadow: 3px 4px 0px 0px #1564ad;
  -moz-box-shadow: 3px 4px 0px 0px #1564ad;
  box-shadow: 3px 4px 0px 0px #1564ad;
  font-family: Arial;
  color: #ffffff;
  font-size: 16px;
  padding: 12px 30px 12px 30px;
  border: solid #337bc4 1px;
  text-decoration: none;
}

.button:hover {
  color: #ffffff;
  background: #378de5;
  text-decoration: none;
}</style>
