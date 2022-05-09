<template>
	<div id="header">
		<HeaderNav ref="res" @toSearch='toSearch'/>
	</div>

	<div id="body">
		<router-view :inData="inData" @outSearch='outSearch' @toRead='toRead'/>
	</div>
</template>

<script setup lang="ts">
import {ref,watch,reactive} from 'vue';
import HeaderNav from '@/components/nav/HeaderNav.vue'; 
import router from '@/router/index.ts';


let inData = reactive({
	inDetail:[]
});

let res = ref(null);




/* 通过搜索框，跳转搜索页 */
const toSearch = (outData) =>{
	inData.inDetail = outData
    router.push({name:'searchView'})
}

/* 接收从searchView传来的方法，跳转到bookView */
const outSearch = (outData) =>{
	inData.inDetail = outData
	router.push({name:'bookView'})
}

/* 接受章节信息，进入具体内容 */
const toRead = (outData) =>{
	console.log(outData)
	inData.inDetail = outData
	router.push({name:'readView'})
}

</script>

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