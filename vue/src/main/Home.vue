<!-- 承载view的主页面
     主要用于调用子组件以及传递数据-->

<script setup lang="ts">
import {ref,watch,reactive} from 'vue';
import HeaderNav from '@/components/nav/HeaderNav.vue'; 
import router from '@/router/index.ts';

let inData = reactive({
	inDetail:[]
});

/* 通过搜索框，跳转搜索页 */
const toSearchView = (outData) =>{
	inData.inDetail = outData
    router.push({name:'searchView'})
}

/* 接收从searchView传来的方法，跳转到bookView */
const toBookView = () =>{
	router.push({name:'bookView'})
}

/* 接受章节信息，进入具体内容 */
const toReadView = (outData) =>{
	inData.inDetail = outData
	router.push({name:'readView'})
}

const toUserView = () =>{
	router.push({name:'userView'})
}

</script>
<template>
	<div id="header">
		<HeaderNav 
		@toSearchView='toSearchView'
		@toUserView='toUserView'/>
	</div>

	<div id="body">
		<router-view :inData="inData" 
		@toBookView='toBookView' 
		@toReadView='toReadView'/>
	</div>
</template>



<style scoped>
	#header{
		margin: auto;
		width: 1400px;
	}
	#body{
		margin: auto;
		width: 1400px;
	}
</style>