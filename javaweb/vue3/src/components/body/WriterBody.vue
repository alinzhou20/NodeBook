<template>
	
	<el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
	  <el-tab-pane label="我的作品"  name="first">
		  <el-row :gutter="20" style="margin-top: 20px;">
		  	<el-col :span="6" v-for="item in Data.Detail" :key="item">
		  		<div class="item">
		  			<el-card :body-style="{ padding: '0px'}" style='background-color: #ffffff6e; border: 0;'>
		  			<img style="width: 160px; height: 240px; " :src="getImg(item.imgUrl)" class="image"/>
		  			<div>
		  				<span>{{item.name}}</span>
		  				<div class="bottom">
		  					<el-button type="text" class="button" @click="getBookChapters(item.id)">继续创作</el-button>
		  				</div>
		  			</div>
		  		  </el-card>
		  		</div>
		  	</el-col>			
		  </el-row>
	  </el-tab-pane>
	  <el-tab-pane label="我的账户" name="second">
			 Config
		 </el-tab-pane>
	</el-tabs>
	
	
	<el-dialog v-model="chapter" title="作品信息">
	    <el-table :data="Data.chapterDetail" style="width: 100%">
	   <el-table-column prop="list" label="章节号" width="180" />
	   <el-table-column prop="title" label="章节名" width="180" />
	   <el-table-column align="right">
	         <template #default="scope">
	           <el-button
	             size="small"
	             type="danger"
	             @click="changeChapter(scope.$index, scope.row)"
	             >点击修改</el-button
	           >
			   <el-button
			     size="small"
			     type="danger"
			     @click="deleteChapter(scope.$index, scope.row)"
			     >点击删除</el-button
			   >
	         </template>
	       </el-table-column>
	     </el-table>
		 <el-button type="primary" @click="addChapter()">创建新章节</el-button>
	</el-dialog>
	
	<el-dialog v-model="newchapter" title="作品信息">
	    <el-form
	       label-width="120px"
	       class="demo-ruleForm"
	     >
		 <el-form-item label="章节序号" >
		   <el-input v-model="list"/>
		 </el-form-item>
	       <el-form-item label="章节名称" >
	         <el-input v-model="title"/>
	       </el-form-item>
		   <el-form-item label="设计价格" >
		     <el-input v-model="price"/>
		   </el-form-item>
	       <el-form-item label="正文">
	        <div class="text-area">
	            <textarea style="width:540px;height:80px" v-model="content" placeholder="正文内容...">
	            </textarea>
	        </div>
	       </el-form-item>
		   <el-button type="primary" @click="addNewChapter()">创建</el-button>
	     </el-form>
	</el-dialog>
	
	
</template>

<script setup lang="ts">
	import {ref,onMounted,reactive,require,toRaw} from 'vue'
	import type { TabsPaneContext } from 'element-plus'
	import axios from 'axios';
	const chapter = ref(false)
	const newchapter = ref(false)
	const title = ref('')
	const list = ref('')
	const price = ref('')
	const content = ref('')
	
	const handleClick = (tab: TabsPaneContext, event: Event) => {
	  console.log(tab, event)
	}
	const activeName = ref('first')
	var Data = reactive({  //存储返回值
	      Detail: [],
		  chapterDetail: [],
	    });
		
	var Data2 = reactive({  //存储返回值
	      Detail: [],
	    });
	onMounted(()=>{
		axios.get('/api/writer',{
			params:{
				writerId: window.sessionStorage.getItem('writer'),
				method: 'getWriterBook'
			}
		}).then(res=>{ 
			/* 此处可以获取外部的Data */
			Data.Detail = JSON.parse(JSON.stringify(res.data.data));
			
		}).catch(err=>{console.log(err);})
		
		
	}) 
	
	let bookID = ref(null);
	const getBookChapters =  function(bookId){
		chapter.value=true
		bookID.value = bookId
		axios.get('/api/chapter',{
			params:{
				bookId: bookId,
				method: 'searchByBookId'
			}
		}).then(res=>{ 
			/* 此处可以获取外部的Data */
			Data.chapterDetail = JSON.parse(JSON.stringify(res.data.data));
		}).catch(err=>{console.log(err);})
	}
	
	interface Capter {
	  id: number
	}
	
	const addChapter = () => {
		newchapter.value=true
	}
	const addNewChapter = () => {
		axios.post('/api/chapter',
			{
				bookId: bookID,
				method: 'addNewChapter',
				title:title,
				list :list ,
				price:price,
				content :content,
			},{headers: {'Content-Type': 'application/json; charset=utf-8'}},
		).then(res=>{ 
			/* 此处可以获取外部的Data */
			Data2.Detail = JSON.parse(JSON.stringify(res.data.data));
			console.log(Data2.Detail)
		}).catch(err=>{console.log(err);})

	}
	
	
	const changeChapter = (index: number, row: Capter) => {
		axios.get('/api/chapter',{
			params:{
				chapterId: toRaw(row).id,
				method: 'changeChapter'
			}
		}).then(res=>{ 
			/* 此处可以获取外部的Data */
			Data.chapterDetail = JSON.parse(JSON.stringify(res.data.data));
		}).catch(err=>{console.log(err);})
	}
	
	const deleteChapter = (index: number, row: Capter) => {
		axios.get('/api/chapter',{
			params:{
				chapterId: toRaw(row).id,
				method: 'deleteChapter'
			}
		}).then(res=>{ 
			/* 此处可以获取外部的Data */
			Data.chapterDetail = JSON.parse(JSON.stringify(res.data.data));
		}).catch(err=>{console.log(err);})
	}
	
	const getImg = function(url){
		/* 需要注意的 是getImg的方法需要放在onmounted之后 */
		let img = require('@/assets/ImgData/'+url);
		return img;
	}
</script>

<style>
	.demo-tabs > .el-tabs__content {
	margin: auto;
	width: 1300px;
	background-color: aquamarine;
	}
	
	.el-tabs__nav{
		margin-left: 400px;
	}
	.el-tabs__item {
		margin-top: 20px;
		margin-left: 100px;
		font-size: 30px;
		color: aliceblue;
	}
	
</style>