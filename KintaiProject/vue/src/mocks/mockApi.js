// モック用ファイル

// ログイン用モック
export const mockLogin = async ({ userId, password }) => {
	if (userId === 'test' && password === '1234') {
		return {
			success: true,
			user: {
				// ログイン用ユーザID
				userId: 'test',
				
				// ログイン用パスワード
				password: '1234',
				
				// 社員ID
				empId:'1234567890',
				
				// 役職 本当は1～5
				role: 'チーフ',
				
				// 社員姓
				empLname: '上坂 祐司',
				
				// チームID
				teamId: '00001',
				
				// チーム名
				teamName: 'セクション1'
			}
		}
	} else {
		return { success: false }
	}
}