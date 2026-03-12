<template>
	<div class="page">

		<!-- ヘッダー -->
		<CommonHeader 
		title=" 勤怠連絡参照（一覧）" 
		:showUser="true" 
		:user="user"
	/>

	<!-- メッセージエリア -->
	<div class="message-area" style="border: 1px solid black; padding: 10px; margin-top: 5px;">
		検索条件を入力してください。
	</div>
	
		<!-- 検索条件 -->
		<section class="card" style="border: 1px solid black;">
			<div class="card-title">検索条件</div>

			<div class="form-row">
				<label class="checkbox-item">
					<input type="checkbox" v-model="filters.selfOnly" />
					自身のみ
				</label>
			</div>

			<div class="form-row">
				<div class="form-item">
					<label>日付</label>
					<input type="date" v-model="filters.dateFrom" />
					<span class="range-separator">～</span>
					<input type="date" v-model="filters.dateTo" />
				</div>

				<div class="form-item">
					<label>所属チーム</label>
					<select v-model="filters.team">
						<option value="">セクション1</option>
					</select>
				</div>

				<div class="form-item" v-if="!isEmployee">
					<label>名前</label>
					<input
						type="text"
						v-model="filters.name"
					/>
				</div>
			</div>

			<div class="button-row">
				<button class="btn btn-primary" @click="search">検索</button>
				<button class="btn btn-secondary" @click="clear">クリア</button>
				<button class="btn" @click="goBack">戻る</button>
			</div>
		</section>

		<!-- 検索結果 -->
		<section class="card" style="border: 1px solid black;" v-if="showResults">
			<div class="result-header">
				<div class="card-title">検索結果</div>
				<button class="btn btn-secondary" @click="exportFile">ファイル出力</button>
			</div>

			<div class="result-count">{{ filteredData.length }}件</div>

			<div class="table-wrapper">
				<table>
					<thead>
						<tr>
							<th>名前</th>
							<th>所属チーム</th>
							<th>日付</th>
							<th>理由</th>
							<th>電車</th>
							<th>遅延時間</th>
							<th class="text-center">詳細</th>
						</tr>
					</thead>
					<tbody>
						<tr v-for="(row, index) in filteredData" :key="index">
							<td>{{ row.name }}</td>
							<td>{{ row.team }}</td>
							<td>{{ row.date }}</td>
							<td>{{ row.reason }}</td>
							<td>{{ row.train }}</td>
							<td>{{ row.delay }}</td>
							<td class="text-center">
								<button class="btn-link" @click="goDetail(row)">詳細</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</section>
	</div>
</template>

<script setup>
import { ref, computed, onMounted } from "vue";
import CommonHeader from '@/components/common/CommonHeader.vue'	// 共通ヘッダー
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

const showResults = ref(false); // 初期は非表示
// モックデータ
const mockData = ref([
	{
		name: "山田 太郎",
		team: "チームA",
		date: "2025/01/20",
		reason: "電車遅延",
		train: "山手線",
		delay: "20分",
	},
	{
		name: "佐藤 花子",
		team: "チームB",
		date: "2025/01/21",
		reason: "人身事故",
		train: "中央線",
		delay: "35分",
	},
	{
		name: "鈴木 次郎",
		team: "チームA",
		date: "2025/01/22",
		reason: "信号トラブル",
		train: "丸ノ内線",
		delay: "15分",
	},
	{
		name: "田中 美咲",
		team: "チームC",
		date: "2025/01/23",
		reason: "強風による遅延",
		train: "京王井の頭線",
		delay: "25分",
	},
	{
		name: "高橋 健",
		team: "チームB",
		date: "2025/01/24",
		reason: "踏切点検",
		train: "総武線",
		delay: "10分",
	},
	{
		name: "中村 彩",
		team: "チームC",
		date: "2025/01/25",
		reason: "車両点検",
		train: "埼京線",
		delay: "30分",
	}
]);

const kintaiListRole = {
	EMPLOYEE: 1, // 社員
	CHIEF: 2, // チーフ
	LEADER: 3, // リーダー	
	AREAMANAGER: 4, // 	エリアマネージャー
	MANAGER: 5 // マネージャー
}

// 社員判定
const isEmployee = computed(() => {
	if(user.value != null){
		return user.value.role === kintaiListRole.EMPLOYEE
	}
	return true
})

// フィルタ条件
const filters = ref({
	selfOnly: false,
	dateFrom: "",
	dateTo: "",
	team: "",
	name: "",
});

// 検索処理（簡易フィルタ）
const filteredData = computed(() => {
	return mockData.value.filter((row) => {
		const matchName = filters.value.name
			? row.name.includes(filters.value.name)
			: true;
		const matchTeam = filters.value.team
			? row.team === filters.value.team
			: true;
		return matchName && matchTeam;
	});
});

const search = () => {
	// モックなので computed が自動反映
	showResults.value = true; // 検索結果表示
};

const clear = () => {
	filters.value = {
		selfOnly: false,
		dateFrom: "",
		dateTo: "",
		team: "",
		name: "",
	};
	showResults.value = false; // 検索結果を隠す
};



const goBack = () => {
	window.history.back();
};

const exportFile = () => {
	alert("ファイル出力（モック）を実行しました。");
};

const goDetail = (row) => {
	sessionStorage.setItem("loginUser", JSON.stringify(user.value))
	sessionStorage.setItem('detail', JSON.stringify(row.value))
	router.push('/kintai-detail')
};
</script>

<style scoped>
.page {
	padding: 24px;
	background: #f5f5f5;
	display: grid;
	grid-template-rows: auto auto auto 1fr; /* ヘッダー、メッセージ、検索、結果 */
	row-gap: 16px; /* 子要素間の縦間隔 */
}


.page-title {
	font-size: 20px;
	font-weight: 600;
	margin-bottom: 16px;
}

.message-area {
	gap: 20px;
}

.card {
	background: #fff;
	padding: 16px 20px;
	border-radius: 4px;
	margin-bottom: 16px;
	box-shadow: 0 1px 3px rgba(0,0,0,0.08);
}

.card-title {
	font-size: 16px;
	font-weight: 600;
	margin-bottom: 12px;
	border-left: 4px solid #8ebc70;
	padding-left: 8px;
}

.form-row {
	display: flex;
	flex-wrap: wrap;
	gap: 16px 32px;
	margin-bottom: 12px;
}

.form-item {
	display: flex;
	align-items: center;
	min-width: 220px;
}

.form-item label {
	min-width: 90px;
	font-size: 13px;
	color: #555;
}

.form-item input,
.form-item select {
	padding: 4px 8px;
	font-size: 13px;
	border: 1px solid #ccc;
	border-radius: 3px;
}

.checkbox-item {
	display: flex;
	align-items: center;
	gap: 4px;
}

.button-row {
	display: flex;
	gap: 8px;
	justify-content: flex-end;
}

.btn {
	padding: 6px 16px;
	font-size: 13px;
	border-radius: 3px;
	border: 1px solid transparent;
	cursor: pointer;
	background-color: #e0e0e0;
}

.btn-primary {
	background-color: #8ebc70;
	color: #fff;
}

.btn-secondary {
	background-color: #fff;
	border-color: #bdbdbd;
}

.btn-link {
	background: none;
	border: none;
	color: #1976d2;
	cursor: pointer;
	text-decoration: underline;
}

.result-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.result-count {
	font-size: 13px;
	color: #555;
}

.table-wrapper {
	overflow-x: auto;
	margin-top: 8px;
}

table {
	width: 100%;
	border-collapse: collapse;
	font-size: 13px;
}

thead {
	background-color: #eeeeee;
}

th, td {
	padding: 6px 8px;
	border: 1px solid #ddd;
	white-space: nowrap;
}

tbody tr:nth-child(even) {
	background-color: #fafafa;
}

.text-center {
	text-align: center;
}
</style>