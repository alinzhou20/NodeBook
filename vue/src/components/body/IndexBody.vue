<template>
	<div id="index">

	<el-row>
		
		<el-col :span="2" :offset="1">
			<el-table :data="tableData" size="large">
				<el-table-column prop="date" label="玄幻排行"/>
			</el-table>
		</el-col>
	
		<el-col :span="19" :offset="1">
			<el-row :gutter="20" style="margin-top: 20px;">
				<el-col :span="6" >
					<div class="item">
						<el-card :body-style="{ padding: '0px'}" style='background-color: #ffffff6e; border: 0;'>
						<img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image"/>
						<div>
							<span>龙族</span>
							<div class="bottom">
								<el-button type="text" class="button" @click="toRead">点击查看</el-button>
							</div>
						</div>
					  </el-card>
					</div>
				</el-col>
				<el-col :span="6">
					<div class="item">
						<el-card :body-style="{ padding: '0px'}" style='background-color: #ffffff6e; border: 0;'>
						<img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image"/>
						<div>
							<span>龙族</span>
							<div class="bottom">
								<el-button type="text" class="button">点击查看</el-button>
							</div>
						</div>
					  </el-card>
					</div>
				</el-col>
				<el-col :span="6">
					<div class="item">
						<el-card :body-style="{ padding: '0px'}" style='background-color: #ffffff6e; border: 0;'>
						<img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image"/>
						<div>
							<span>龙族</span>
							<div class="bottom">
								<el-button type="text" class="button">点击查看</el-button>
							</div>
						</div>
					  </el-card>
					</div>
				</el-col>
				<el-col :span="6">
					<div class="item">
						<el-card :body-style="{ padding: '0px'}" style='background-color: #ffffff6e; border: 0;'>
						<img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image"/>
						<div>
							<span>龙族</span>
							<div class="bottom">
								<el-button type="text" class="button">点击查看</el-button>
							</div>
						</div>
					  </el-card>
					</div>
				</el-col>							
			</el-row>
			<el-row :gutter="20" style="margin-top: 20px;">
				<el-col :span="6" >
					<div class="item">
						<el-card :body-style="{ padding: '0px'}" style='background-color: #ffffff6e; border: 0;'>
						<img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image"/>
						<div>
							<span>龙族</span>
							<div class="bottom">
								<el-button type="text" class="button">点击查看</el-button>
							</div>
						</div>
					  </el-card>
					</div>
				</el-col>
				<el-col :span="6">
					<div class="item">
						<el-card :body-style="{ padding: '0px'}" style='background-color: #ffffff6e; border: 0;'>
						<img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image"/>
						<div>
							<span>龙族</span>
							<div class="bottom">
								<el-button type="text" class="button">点击查看</el-button>
							</div>
						</div>
					  </el-card>
					</div>
				</el-col>
				<el-col :span="6">
					<div class="item">
						<el-card :body-style="{ padding: '0px'}" style='background-color: #ffffff6e; border: 0;'>
						<img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image"/>
						<div>
							<span>龙族</span>
							<div class="bottom">
								<el-button type="text" class="button">点击查看</el-button>
							</div>
						</div>
					  </el-card>
					</div>
				</el-col>
				<el-col :span="6">
					<div class="item">
						<el-card :body-style="{ padding: '0px'}" style='background-color: #ffffff6e; border: 0;'>
						<img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image"/>
						<div>
							<span>龙族</span>
							<div class="bottom">
								<el-button type="text" class="button">点击查看</el-button>
							</div>
						</div>
					  </el-card>
					</div>
				</el-col>							
			</el-row>
		</el-col>
	
	</el-row>			
		
		
	</div>
</template>
<script setup lang="ts">

const tableData = [
  {
    date: '龙族',

  },
  {
    date: '斗罗大陆',

  },
  {
    date: '斗破苍穹',

  },
  {
    date: '大王饶命啊',

  },
]

import router from '@/router/index.ts'
function toRead(){
   router.push({name:'read'})
}
</script>



<style scoped>
	#index{
		margin: auto;
		margin: 0;
		margin-top: 20px;
		width: 1400px;
	}	
	#index div{
		margin: auto;
	}
	.image{
		width: 200px;
	}
	#item{
		padding-top: 20px;
	}

</style>