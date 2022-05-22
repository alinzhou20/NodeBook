<script setup lang="ts">
	
import { ref } from 'vue'
import BookNav from '@/components/nav/BookNav.vue'
import BookBody from '@/components/body/BookBody.vue'
import BookDetail from '@/components/body/BookDetail.vue'
const activeName = ref('first') //查看作品信息

</script>

<template>
<BookNav/>
<div>
	<el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick" style='margin-top:20px'>
	    <el-tab-pane label="作品信息" name="first">
			<BookDetail/>
		</el-tab-pane>
	    <el-tab-pane label="章节目录" name="second">
			<BookBody/>
		</el-tab-pane>
	</el-tabs>
</div>

</template>