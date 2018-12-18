package dentaku2;

public class Contents {

	//フラグ各種。
	private boolean errorFlag , enzanFlag , dotFlag ,minusFlag ;
	//配列カウント変数各種。
	private byte tmpValCount, valSequenceCount;
	//数値各種。
	private double val, subtotal, foreVal;
	//数値が整数の時に、小数点以下の０を表示させない為の整数数値各種。
	private long valLong, subtotalLong;
	//Bcボタン使用時のバックスペース用入力値保持配列。
	private char tmpVal[] = new char[100];
	//計算の流れを保持する配列。
	private String valSequence[] = new String[200];
	//計算結果と計算の軌跡変数。
	
	private String label1Value,label2Value;
	/****************************************
	* 		グローバル変数の宣言ここまで	*
	****************************************/
	
	
	/****************************************
	* 				ゲッター				*
	****************************************/
	//ラベル１に値を表示させるゲッター。
	public String getLabel1Value() {
		return this.label1Value;
	}
	//ラベル２に値を表示させるゲッター。
	public String getLabel2Value() {
		return this.label2Value;
	}
	//エラーフラグのゲッター。
	public boolean getEroorFlag() {
		return this.errorFlag;
	}
	
	public  Contents(){
		initialization();
	}
	
	/************************************************************************
	 *			数字が入力された時、一時的数値配列に追加するメソッド。		*
	 ************************************************************************/
	public void addNumber(String singleVal) { //入力された値をtmpVal配列に格納していくメソッド。
		String str;
		if (tmpValCount == 99) { //入力された数字の桁が１００に達した場合、
			label1Value = "Error"; //エラー表示をして処理を抜ける。
			errorFlag = true;
			return;
		}
		if (singleVal == "０" && tmpValCount == 0) { //入力された数字が０で、前回までに入力された値が０である場合、
			label1Value = "0";
			return;
		}
		enzanFlag = false;
		tmpVal[tmpValCount] = singleVal.charAt(0); //値の格納。
		tmpVal[tmpValCount++] -= 65248; //全角数字をキャラクター型の半角数字に変換を行う。値を格納する配列の次の番地を指定する為のカウントアップ。

		showLabel1();//現在入力中の数値の表示を更新。
		str = new String(tmpVal);
		if (val < 0) {
			str = "-" + str;
		}
		label1Value = str;
	}
	
	
	
	/************************************************************************
	 *			数字以外が入力された時の大体の演算処理を行う。				*
	 *			役目の追加メソッド											*
	 ************************************************************************/
	public void addFunction(String singleStr) { //数字以外の演算子等が入力された場合のメソッド。
		String str;
		double val1, val2;
		byte i;

		switch (singleStr) {
		case "％":
			if (foreVal != 0) {
				val = foreVal * (val / 100);
				label1Value = longOrDouble();
				while (tmpValCount != 0) {
					tmpVal[--tmpValCount] = 0;
				}
				dotFlag = false;
			}
			break;
		case "．":
			if (dotFlag == true) {
				return;//「.」が２度目押された場合、処理を抜ける。
			}
			if (tmpValCount == 0) { //一桁目から「.」が押された場合。
				tmpVal[tmpValCount++] = '0'; //一桁目に０を格納して、「０．」の状態にする。
				label1Value = "0";
			}
			dotFlag = true;
			enzanFlag = false;
			tmpVal[tmpValCount++] = '.';
			label1Value += "."; //「.」が押された時に.のみを追加で表記する為の処理。
			return;
		case "±":
			if (val == 0) {
				return;
			}
			val = -val;
			if (val < 0) {//入力中の値がマイナスなら、
				minusFlag = true;//マイナスフラグを真にする。
			} else {
				minusFlag = false;
			}
			if (tmpVal[0] == 0) {//小計が０ではなく、かつ入力が無い場合。
				label1Value = longOrDouble();
			} else {
				showLabel1();
			}
			break;
		case "√":
			val = Math.sqrt(val);
			label1Value = longOrDouble();
			break;
		case "1/x":
			if (val != 0) {
				val = 1 / val;
			}
			label1Value = longOrDouble();
			break;
		case "x²":
			if (val != 0) {
				val *= val;
			}
			if (Double.isInfinite(val) == true) {
				label1Value = "Error:Infinity";
				errorFlag = true;
			} else {
				label1Value = longOrDouble();
			}
			break;
		case "Bc": //Bcかバックスペースキーが押された時、直近の入力された内容を削除する処理。
			if (tmpValCount <= 1) { //入力された数字が一個以下であるなら、
				tmpValCount = 0; //数字が一個入力されていた場合と何も入力されていなかった時の処理とを兼用する。
				tmpVal[tmpValCount] = '0'; //処理が終わったらそのままメソッドを抜ける。
				if (valSequenceCount > 0) {
					enzanFlag = true;

				}
				showLabel1();
				return;
			}

			if (tmpVal[--tmpValCount] == '.') { //削除対象が「.」の場合、
				dotFlag = false; //dotFlagをリセットする。
				if (tmpVal[0] == '0' && valSequenceCount > 0) {
					enzanFlag = true;
					tmpVal[tmpValCount] = 0;
					clearEntry();
					return;
				}
			}
			tmpVal[tmpValCount] = 0; //直近に入力された内容を削除する。
			if (tmpVal[tmpValCount - 1] == '.') { //削除後手前の値が「.」の場合、整数で表示させる処理を行う。
				showLabel1(); //long型で出力される為、整数での表示となる。
				label1Value += "."; //その為ラベルの末尾にドットを付け加える。
			} else { //削除後の手前の値が数字である場合、
				showLabel1(); //そのままラベルに出力を行う。
				str = new String(tmpVal);
				if (val < 0) {
					str = "-" + str;
				}
				label1Value = str;
			}
			break;
		case "Ｃ":
			allReset();
			showLabel1();
			showLabel2();
			break;
		case "CE": //クリアエントリーが押された場合の処理。入力された数字を削除する。
			clearEntry();
			if (valSequenceCount > 0) {
				enzanFlag = true;
			}
			break;
		case "＋":
		case "－":
		case "×":
		case "÷":
			if (enzanFlag == true) {
				valSequence[valSequenceCount - 1] = singleStr;
				showLabel2();
				return;
			}
			if (val == 0) {//入力された値が０ならばそのまま処理を抜ける。
				return;
			}
			valSequence[valSequenceCount++] = longOrDouble();
			valSequence[valSequenceCount++] = singleStr;//演算子を入れてカウンターをカントアップする。
			enzanFlag = true;
			foreVal = val;
			i = 3;
			subtotal = Double.valueOf(valSequence[0]);
			while (valSequenceCount >= i) {
				if (valSequence[i - 2] == "＋") {
					subtotal += Double.valueOf(valSequence[i - 1]);
				} else if (valSequence[i - 2] == "－") {
					subtotal -= Double.valueOf(valSequence[i - 1]);
				} else if (valSequence[i - 2] == "×") {
					subtotal *= Double.valueOf(valSequence[i - 1]);
				} else if (valSequence[i - 2] == "÷") {
					subtotal /= Double.valueOf(valSequence[i - 1]);
				}
				i += 2;
			}
			showLabel2();
			clearEntry();
			subtotalLong = (long) subtotal;
			if (subtotalLong == subtotal) {
				label1Value = String.valueOf(subtotalLong);
			} else {
				label1Value = String.valueOf(subtotal);
			}
			break;
		case "＝":
			if (valSequenceCount < 2 || valSequenceCount == 2 && tmpValCount == 0 && val == 0) {
				return;
			}
			if (enzanFlag == true) {
				valSequence[--valSequenceCount] = null;
				enzanFlag = false;
			} else if (val == 0) {//直前の入力が演算子ではなく、入力数値が０の時に「＝」処理を抜ける。
				return;
			}
			if (val != 0) {
				valSequence[valSequenceCount++] = longOrDouble();
				clearEntry();
			}
			i = 1;
			while (i < valSequenceCount) {
				val1 = Double.valueOf(valSequence[i - 1]);//探し出した演算子の一個手前と、
				val2 = Double.valueOf(valSequence[i + 1]);//一個後ろの数値を取り出し、
				if (valSequence[i] == "×") {//演算子×の場合。
					substituteMixValue((val1 * val2), i);
				} else if (valSequence[i] == "÷") {//÷の場合。
					substituteMixValue((val1 / val2), i);
				} else if (valSequence[i] == "＋") {//演算子＋の場合。
					substituteMixValue((val1 + val2), i);
				} else if (valSequence[i] == "－") {//ーの場合。
					substituteMixValue((val1 - val2), i);
				}
			}
			val = Double.valueOf(valSequence[0]);
			valLong = (long) val;
			if (val == valLong) {//小数か整数かの判定を行い、当てはまる型での表示を行う。
				label1Value = String.valueOf(valLong);
			} else {
				label1Value = String.valueOf(val);
			}
			valSequence[0] = null;
			valSequenceCount = 0;
			label2Value = "";
			foreVal = 0;
			if (Double.isInfinite(val) == true) {
				label1Value ="Error:Infinity";
				errorFlag = true;
			}
		}
	}

	/************************************************************************
	 *			演算後の代入と代入後の空白を詰める処理のメソッド。			*
	 *			仮引数には演算結果が入る。									*
	 ************************************************************************/

	void substituteMixValue(double valMix, byte i) {
		byte j;
		valSequence[i - 1] = Double.toString(valMix);
		valSequence[i] = null;
		valSequence[i + 1] = null;
		j = i;
		valSequenceCount -= 2;
		while (valSequence[j + 2] != null) {
			valSequence[j] = valSequence[j + 2];
			valSequence[j + 2] = null;
			j++;
		}
	}

	/************************************************************************
	 *			小数か整数かを判定するメソッド。							*
	 *			例：整数であっても、double型では「123.0」と表示されて		*
	 *			しまう為に、それを避ける方法としてlong型にキャストする事で	*
	 *			「123」と小数点以下の０を表示させない様にする。				*
	 ************************************************************************/
	private String longOrDouble() {
		valLong = (long) val;
		if (valLong == val) {
			return String.valueOf(valLong);
		}
		return String.valueOf(val);
	}

	/************************************************************************
	 *			CEを押された時の動作メソッド。								*
	 *			一時入力の配列tmpValのリセットとその他関係する				*
	 *			フラグのリセットを行う。									*
	 ************************************************************************/
	void clearEntry() {
		while (tmpValCount != 0) {
			tmpVal[--tmpValCount] = 0;
		}
		dotFlag = false;
		minusFlag = false;
		val = 0;
		label1Value = "0";
	}

	/************************************************************************
	 *			初期化メソッド。											*
	 *			Ｃボタンを押された場合に行われる処理。						*
	 ************************************************************************/
	void allReset() {
		while (tmpValCount != 0) {//配列tmpValと、カウンターtmpValCountのリセット。
			tmpVal[--tmpValCount] = 0;
		}
		while (valSequenceCount != 0) {//配列valSequenceと、カウンターvalSequenceCountのリセット。
			valSequence[--valSequenceCount] = null;
		}
		initialization();
	}
	
	void initialization() {
		tmpVal[0] = '0';
		
		label1Value = "0";
		label2Value ="0";
		
		tmpValCount = 0;
		valSequenceCount = 0;
		
		val = 0;
		subtotal = 0;
		foreVal = 0;
		
		valLong = 0;
		subtotalLong = 0;
		
		errorFlag = false;
		enzanFlag = false;
		dotFlag = false;		
		minusFlag = false;
	}

	/************************************************************************
	 *			現在入力または合計をラベル１に表示させるメソッド			*
	 *			tmpValに格納された値を一つずつvalに取り出す、				*
	 *			valの値を左にシフトさせる、小数点が出た場合					*
	 *			小数点以下に値を追加していく。								*
	 ************************************************************************/
	void showLabel1() {
		String str;
		val = 0;
		valLong = 0;
		str = new String(tmpVal);
		if (Double.valueOf(str) == 0) {//入力値が空っぽの場合、
			str = "0";//ラベル１に０を出力する為に文字列０を代入する。
			minusFlag = false;//バックスペース等で０になった場合を想定して、マイナスフラグを折る。
		}
		val = Double.valueOf(str);
		if (minusFlag == true) {//「±」を押した時の数字の反転を行う。
			val = -val;
		}
		valLong = (long) val;
		if (valLong == val) {
			label1Value = String.valueOf(valLong);
		} else {
			if (minusFlag == true) {
				str = "-" + str;
			}
			label1Value = str;
		}
	}

	/************************************************************************
	 *			これまでに入力された数値と演算子をlabel2Valueに入れる		*
	 ************************************************************************/
	void showLabel2() {
		byte i;
		String str = "";
		for (i = 0; i < valSequenceCount; i++) {
			str += valSequence[i];
		}
		label2Value = str;
	}


}
