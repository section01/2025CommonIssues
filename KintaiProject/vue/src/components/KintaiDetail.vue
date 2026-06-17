<template>
  <div class="attendance-container">

	<!-- ヘッダー -->
	<CommonHeader 
		title=" 勤怠連絡参照（詳細）" 
		:showUser="true" 
		:user="user"
	/>

    <!-- データ表示エリア -->
    <div class="form-area">
      <div class="form-row">
        <label>日付</label>
        <input type="date" v-model="date" readonly />
      </div>
      <div class="form-row">
        <label>始業時間</label>
        <input type="text" v-model="startTime" placeholder="9時00分" readonly />
      </div>
      <div class="form-row">
        <label>理由</label>
        <input type="text" v-model="reason" placeholder="電車遅延" readonly />
      </div>
      <div class="form-row">
        <label>電車</label>
        <input type="text" v-model="trainLine" placeholder="○○××線" readonly />
      </div>
      <div class="form-row">
        <label>遅延時間</label>
        <input type="text" v-model="delayTime" placeholder="40分" readonly />
      </div>
      <div class="form-row">
        <label>本文</label>
        <textarea v-model="messageBody" rows="5" readonly />
      </div>
    </div>

    <!-- ボタンエリア -->
    <div class="button-area">
      <button @click="handleBack">戻る</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import CommonHeader from '@/components/common/CommonHeader.vue'  // 共通ヘッダー

const router = useRouter()
const user = ref(null)

// ログイン時に保存したユーザ情報を取得
onMounted(() => {
	const storedUser = sessionStorage.getItem("loginUser")
	if (storedUser) {
		user.value = JSON.parse(storedUser)
	}
})

const date = ref('2026-06-17')
const startTime = ref('9時00分')
const reason = ref('電車遅延')
const trainLine = ref('山手線')
const delayTime = ref('30分')
const messageBody = ref(`お疲れ様です。上坂です。
山手線が台風の影響で、30分ほど遅延しているため、遅れます。`)

const handleBack = () => {
  router.push('/menu') // 戻る先はメニュー画面など
}
</script>

<style scoped>
.attendance-container {
  width: 90%;
  margin: 40px auto;
  padding: 20px;
  border: 1px solid #000;
  background-color: #f9f9f9;
  font-family: sans-serif;
}

.message-area {
  padding: 10px;
  font-size: 14px;
  border-bottom: 1px solid #000;
}

.form-area {
  padding: 20px 30px;
}

.form-row {
  display: flex;
  flex-direction: column;
  margin-bottom: 15px;
}

label {
  font-size: 14px;
  margin-bottom: 5px;
}

input,
textarea {
  padding: 5px;
  font-size: 14px;
  border: 1px solid #000;
  background-color: #fff;
}

textarea {
  resize: vertical;
}

.button-area {
  display: flex;
  justify-content: center;
  padding: 20px;
}

button {
  padding: 8px 20px;
  background-color: #d9d9d9;
  border: 1px solid #000;
  cursor: pointer;
}

button:hover {
  background-color: #c0c0c0;
}
</style>