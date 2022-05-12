<template>
<BookNav :inBook="inBook"/>
<BookBody @toReadView="toReadView"/>
</template>

<script setup lang="ts">

import { ref,toRaw,reactive,onBeforeMount} from 'vue'
import axios from 'axios';
import BookNav from '@/components/nav/BookNav.vue'
import BookBody from '@/components/body/BookBody.vue'

const props = defineProps<{
	inData: any
}>();

let inBook =  reactive({
	inDetail:[]
});

/* 接子组件数据，调父组件方法 */

const emits =  defineEmits(['toReadView'])
const toReadView = (outData) => {
	emits('toReadView',outData)
}

/* 父子组件的生命周期有对应的顺序
   beforeCreate->父created->父beforeMount->子beforeCreate->子created->子beforeMount->子mounted->父mounted
*/
console.log(props)
onBeforeMount(()=>{
	axios.get('/api/book/getById',{
		params:{
			bookId: toRaw(props.inData).inDetail
		}
	})
	.then(res=>{ 		
		inBook.inDetail = JSON.parse(JSON.stringify(res.data.data));
		window.sessionStorage.setItem('tar_book', toRaw(inBook.inDetail).id)
	}).catch(err=>{console.log(err);})
	
}) 

/* inBook.inDetail = toRaw(props.Data) */
</script>
