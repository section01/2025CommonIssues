<template>
	<div>
		<div class="common-container menu-container">
			<CommonHeader 
				title="勤怠連絡入力画面" 
				:showUser="true" 
				:user="user"
			/>
		</div>
		<!-- 上部メッセージ -->
		<div style="border: 1px solid black; padding: 10px; margin-top: 5px;">
			勤怠情報を入力してください。
		</div>

		<!-- 入力フォーム -->
		<form @submit.prevent="submit" style="border: 1px solid black; padding: 10px; margin-top: 5px;">
			<div style="margin-bottom: 10px;">
				<label>日付</label>
				<input type="date" v-model="date" required />
			</div>

			<div style="margin-bottom: 10px;">
				<label>始業時間</label>
				<input
					type="number"
					v-model.number="startHour"
					min="0"
					max="23"
					placeholder="時"
					required
					style="width: 50px;"
				/> 時
				<input
					type="number"
					v-model.number="startMinute"
					min="0"
					max="59"
					placeholder="分"
					required
					style="width: 50px; margin-left: 10px;"
				/> 分
			</div>

			<div style="margin-bottom: 10px;">
				<label>理由</label>
				<select v-model="reason" required>
					<option disabled value="">選択してください</option>
					<option value="電車遅延">電車遅延</option>
					<option value="寝坊">寝坊</option>
					<option value="体調不良">体調不良</option>
					<option value="その他">その他</option>
				</select>
			</div>

			<div style="margin-bottom: 10px;">
				<label>電車</label>
				<input
					type="text"
					v-model="trainLine"
					:required="reason === '電車遅延'"
					:disabled="reason !== '電車遅延'"
				/>
			</div>

			<div style="margin-bottom: 10px;">
				<label>遅延時間</label>
				<input
					type="number"
					v-model.number="delayTime"
					required
					min="0"
					style="width: 60px;"
				/> 分
			</div>

			<div style="margin-bottom: 10px;">
				<label>本文</label><br />
				<textarea v-model="body" required rows="5" cols="40" />
			</div>

			<div>
				<button type="submit">登録</button>
				<button type="button" @click="goBack" style="margin-left: 20px;">戻る</button>
			</div>
		</form>
	</div>
</template>

<script setup>
import CommonHeader from '@/components/common/CommonHeader.vue'
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const user = ref(null)

// ログイン時に保存したユーザ情報を取得
onMounted(() => {
	const storedUser = sessionStorage.getItem("loginUser")
	if (storedUser) {
		user.value = JSON.parse(storedUser)
	}
})

// 日付フォームなど
const date = ref(getTodayDate())
const startHour = ref(null)
const startMinute = ref(null)
const reason = ref('')
const trainLine = ref('')
const delayTime = ref(null)
const body = ref('')

// 今日の日付を返す
function getTodayDate() {
	const today = new Date()
	const y = today.getFullYear()
	const m = String(today.getMonth() + 1).padStart(2, '0')
	const d = String(today.getDate()).padStart(2, '0')
	return `${y}-${m}-${d}`
}

// フォーム送信
function submit() {
	if (startHour.value === null) {
		alert('始業時間は必須です')
		return
	}
	if (!reason.value) {
		alert('理由は必須です')
		return
	}
	if (reason.value === '電車遅延' && !trainLine.value) {
		alert('電車は理由が電車遅延の場合必須です')
		return
	}
	if (delayTime.value === null) {
		alert('遅延時間は必須です')
		return
	}
	if (!body.value) {
		alert('本文は必須です')
		return
	}
	alert('登録処理を実行します')
	
	goMenu()
}

// メニューに戻る
function goMenu() {
	sessionStorage.setItem("loginUser", JSON.stringify(user.value))
	router.push('/menu')
}

function goBack() {
	goMenu()
}
</script>

<style scoped>
label {
	display: inline-block;
	width: 70px;
	vertical-align: top;
}
</style>