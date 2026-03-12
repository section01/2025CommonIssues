<template>
	<div class="common-container menu-container">
		<CommonHeader 
		title="メニュー" 
		:showUser="true" 
		:user="user"
		/>
	</div>

	<!-- メッセージエリア -->
	<div class="message-area" style="border: 1px solid black;">
		勤怠を新しく登録する場合は「勤怠連絡入力」を選択してください。<br>
		過去に登録した勤怠連絡を確認する場合は「勤怠連絡参照」を選択してください。
	</div>

	<!-- ボタンエリア -->
	<div class="button-area">
		<button class="menu-button" @click="goKintaiInput">勤怠連絡入力</button>
		<button class="menu-button" @click="goKintaiList">勤怠連絡参照</button>
	</div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import CommonHeader from '@/components/common/CommonHeader.vue'	// 共通ヘッダー
import { onMounted, ref } from 'vue' // セッションストレージ


const router = useRouter()
const user = ref(null)

onMounted(() => {
	// ログイン時に保存したユーザ情報を取得
	const storedUser = sessionStorage.getItem("loginUser")
	if (storedUser) {
		user.value = JSON.parse(storedUser)
	}
})

const goKintaiInput = () => {
	sessionStorage.setItem("loginUser", JSON.stringify(user.value))
	router.push('/kintai-input')
}

const goKintaiList = () => {
	sessionStorage.setItem("loginUser", JSON.stringify(user.value))
	router.push('/kintai-list')
}
</script>

<style scoped> 

/* メッセージエリア */
.message-area {
	padding: 10px;
	border-bottom: 1px solid #000;
	font-size: 14px;
}

/* ボタンエリア */
.button-area {
	display: flex;
	justify-content: center;
	gap: 40px;
	padding: 30px;
}

.menu-button {
	padding: 20px 30px;
	font-size: 16px;
	background-color: #d9d9d9;
	border: 1px solid #555;
	cursor: pointer;
}

.menu-button:hover {
	background-color: #c0c0c0;
}
</style>