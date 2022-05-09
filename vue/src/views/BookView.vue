<template>
<BookNav :inBook="inBook"/>
<BookBody :inBook="inBook" @toRead="toRead"/>
</template>

<script setup lang="ts">

import { ref,toRaw,reactive} from 'vue'
import BookNav from '@/components/nav/BookNav.vue'
import BookBody from '@/components/body/BookBody.vue'

const props = defineProps<{
	inData: any
}>();

let inBook =  reactive({
	inDetail:[]
});

/* 接子组件数据，调父组件方法 */
const emits =  defineEmits(['toRead'])
const toRead = (outData) => {
	emits('toRead',outData)
}

inBook.inDetail = toRaw(props.inData)
</script>

<style scoped>
	
</style>