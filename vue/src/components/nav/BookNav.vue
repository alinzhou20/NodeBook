<template>
	<div id="book">
		<el-row :gutter="10">			  
		    <el-col :span="6" >
				<el-image style="width: 160px; height: 200px; " :src="logo_src" />
			</el-col>
			<el-col :span="16" :offset="2">
				<el-row>
					<el-col :span="6">
						<!-- 书名 -->
						{{props.inBook.inDetail.name}}
					</el-col>
					<el-col :span="6">
						<!-- 作者 -->
						{{props.inBook.inDetail.writer}}
					</el-col>
				</el-row>
				
				<el-row>
					<el-col :span="6">
						<!-- 操作 -->
						放入书架
					</el-col>
					<el-col :span="6">
					  <el-button type="primary" @click="toChapter">开始阅读</el-button>
					</el-col>
				</el-row>
		    </el-col>
		</el-row>
        
		<el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick" style='margin-top:20px'>
            <el-tab-pane label="作品信息" name="first">
				
			</el-tab-pane>
            <el-tab-pane label="章节目录" name="second">
				
			</el-tab-pane>
        </el-tabs>
			
	</div>
</template>

<script setup lang="ts">
/* 多表单 */	
import { ref,toRaw } from 'vue'
import type { TabsPaneContext } from 'element-plus'

const activeName = ref('first')

const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log(tab, event)
}

/* 书籍信息 */

const book = ref()
/* 阅读界面 */
import router from '@/router/index.ts'
function toChapter(){	
	router.push({name:'chapter'})
}



/* 获取书籍信息 
   !! 注意，不能两次toRaw*/

const props = defineProps<{
	inBook: any
}>();
</script>

<style scoped>
	#book{
		margin: auto;
		width: 1100px;
		background-color: antiquewhite;
	}
	
</style>