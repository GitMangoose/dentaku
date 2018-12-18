package dentaku2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class MyFrame extends JFrame implements ActionListener, KeyListener {
	/****************************************
	* 			ボタンの作成 				*
	****************************************/
	JButton button0 = new JButton("０");
	JButton button1 = new JButton("１");
	JButton button2 = new JButton("２");
	JButton button3 = new JButton("３");
	JButton button4 = new JButton("４");
	JButton button5 = new JButton("５");
	JButton button6 = new JButton("６");
	JButton button7 = new JButton("７");
	JButton button8 = new JButton("８");
	JButton button9 = new JButton("９");
	JButton buttonReverse = new JButton("±");
	JButton buttonEqual = new JButton("＝");
	JButton buttonPlus = new JButton("＋");
	JButton buttonMinus = new JButton("－");
	JButton buttonDot = new JButton("．");
	JButton buttonMultiply = new JButton("×");
	JButton buttonDivide = new JButton("÷");
	JButton buttonClear = new JButton("Ｃ");
	JButton buttonClearentry = new JButton("CE");
	JButton buttonBack = new JButton("Bc");
	JButton buttonPercent = new JButton("％");
	JButton buttonRoot = new JButton("√");
	JButton buttonJijyo = new JButton("x²");
	JButton buttonSomeshingPer1 = new JButton("1/x");

	JLabel label1 = new JLabel();
	JLabel label2 = new JLabel();

	JPanel p = new JPanel(); //パネルPを作成。


	Contents contents = new Contents();//コンテンツインスタンの生成。
	
	public MyFrame(String title, int x, int y, int width, int height) { //仮引数は、フレームの配置位置横軸、縦軸。フレームのサイズ横軸、縦軸。
		setTitle(title); //フレームのタイトルをセット。
		setSize(width, height);
		setLocationRelativeTo(null);//フレームを中央に配置させる。
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //×ボタンが押された時に終了する。
		setResizable(false); // フレームのサイズを固定する。

		label1.setText(contents.getLabel1Value());
		
		panel(); //パネル精製メソッドの呼び出し。
	}

	void panel() {

		p.setLayout(null); //パネルのデフォルトレイアウトの無効化。自由にレイアウト出来る様にする。
		/****************************************
		 * 		ボタンの位置とサイズの設定 		*
		 ****************************************/
		buttonReverse.setBounds(10, 460, 65, 50); //下から１段目、左から１番目に配置。
		button0.setBounds(85, 460, 65, 50);
		buttonDot.setBounds(160, 460, 65, 50);
		buttonEqual.setBounds(235, 460, 65, 50); //下から１段目、左から４番目に配置。

		button1.setBounds(10, 405, 65, 50); //下から２段目、左から１番目に配置。
		button2.setBounds(85, 405, 65, 50);
		button3.setBounds(160, 405, 65, 50);
		buttonPlus.setBounds(235, 405, 65, 50); //下から２段目、左から４番目に配置。

		button4.setBounds(10, 350, 65, 50); //下から３段目、左から１番目に配置。
		button5.setBounds(85, 350, 65, 50);
		button6.setBounds(160, 350, 65, 50);
		buttonMinus.setBounds(235, 350, 65, 50); //下から３段目、左から４番目に配置。

		button7.setBounds(10, 295, 65, 50); //下から４段目、左から１番目に配置。
		button8.setBounds(85, 295, 65, 50);
		button9.setBounds(160, 295, 65, 50);
		buttonMultiply.setBounds(235, 295, 65, 50);//下から４段目、左から４番目に配置。

		buttonClearentry.setBounds(10, 240, 65, 50);//下から５段目、左から１番目に配置。
		buttonClear.setBounds(85, 240, 65, 50);
		buttonBack.setBounds(160, 240, 65, 50);
		buttonDivide.setBounds(235, 240, 65, 50); //下から５段目、左から４番目に配置。

		buttonPercent.setBounds(10, 185, 65, 50); //下から６段目、左から１番目に配置。
		buttonRoot.setBounds(85, 185, 65, 50);
		buttonJijyo.setBounds(160, 185, 65, 50);
		buttonSomeshingPer1.setBounds(235, 185, 65, 50); //下から６段目、左から４番目に配置。

		/****************************************
		 *アクションイベントを受け取れる様に設定*
		 ****************************************/
		button0.addActionListener(this);
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		button7.addActionListener(this);
		button8.addActionListener(this);
		button9.addActionListener(this);
		buttonReverse.addActionListener(this);
		buttonEqual.addActionListener(this);
		buttonPlus.addActionListener(this);
		buttonMinus.addActionListener(this);
		buttonDot.addActionListener(this);
		buttonMultiply.addActionListener(this);
		buttonDivide.addActionListener(this);
		buttonClear.addActionListener(this);
		buttonClearentry.addActionListener(this);
		buttonBack.addActionListener(this);
		buttonPercent.addActionListener(this);
		buttonRoot.addActionListener(this);
		buttonJijyo.addActionListener(this);
		buttonSomeshingPer1.addActionListener(this);

		/****************************************
		 * 		造ったボタンをパネルに追加 		*
		 ****************************************/
		p.add(buttonReverse); //ボタン「±」
		p.add(button0); //ボタン「０」
		p.add(buttonDot); //ボタン「．」
		p.add(buttonEqual); //ボタン「＝」

		p.add(button1); //ボタン「１」
		p.add(button2); //ボタン「２」
		p.add(button3); //ボタン「３」
		p.add(buttonPlus); //ボタン「＋」

		p.add(button4); //ボタン「４」
		p.add(button5); //ボタン「５」
		p.add(button6); //ボタン「６」
		p.add(buttonMinus); //ボタン「－」

		p.add(button7); //ボタン「７」
		p.add(button8); //ボタン「８」
		p.add(button9); //ボタン「９」
		p.add(buttonMultiply); //ボタン「×」

		p.add(buttonClearentry);//ボタン「CE」
		p.add(buttonClear); //ボタン「Ｃ」
		p.add(buttonBack); //ボタン「Bc」
		p.add(buttonDivide); //ボタン「÷」

		p.add(buttonPercent); //ボタン「％」
		p.add(buttonRoot); //ボタン「√」
		p.add(buttonJijyo); //ボタン「x²」
		p.add(buttonSomeshingPer1); //ボタン「1/x」

		/****************************************
		 * 		ラベルの追加 					*
		 ****************************************/
		label1.setBounds(10, 90, 290, 50);
		label1.setHorizontalAlignment(JLabel.RIGHT);
		label1.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 22)); //ラベル1のフォントの変更。
		p.add(label1);

		label2.setBounds(10, 40, 290, 50);
		label2.setHorizontalAlignment(JLabel.RIGHT);
		label2.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 12)); //ラベル2のフォントの変更。
		p.add(label2);

		p.addKeyListener((KeyListener) this);//そのままではキー入力を受け付けない為、
		p.setFocusable(true); //パネルでキー入寮を受け付ける処理。

		getContentPane().add(p); //パネルPの追加。

	}

	/************************************************************************
	 *			ボタン入力に応じて処理を行うメソッド。						*
	 ************************************************************************/
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (contents.getEroorFlag() && command != "Ｃ") { //エラー発生中につき、Ｃが押されない限りメソッドを抜ける。
			return;
		}
		switch (command) {
		case "０":
		case "１":
		case "２":
		case "３":
		case "４":
		case "５":
		case "６":
		case "７":
		case "８":
		case "９":
			contents.addNumber(command);
			break;
		case "±":
		case "＝":
		case "＋":
		case "－":
		case "．":
		case "×":
		case "÷":
		case "Ｃ":
		case "CE":
		case "Bc":
		case "％":
		case "√":
		case "x²":
		case "1/x":
			contents.addFunction(command);
			break;
		}
		label1.setText(contents.getLabel1Value());
		if (contents.getLabel2Value() != "0" )
			label2.setText(contents.getLabel2Value());
		p.requestFocus();//ボタンに移動してしまったフォーカスをパネルに戻す。
	}



	@Override
	public void keyPressed(KeyEvent e) {
		//エラー状態の場合キー入力処理を受け付けない様にする為、キー入力メソッドを抜ける。
		if (contents.getEroorFlag()) {
			return;
		}
		
		int key = e.getKeyCode();
		//数字コードで受け取ったキーの種類を判別すし、addNumberメソッドにて数値を加算を行う。
		switch (key) {
		case 96:
			contents.addNumber("０");
			break;
		case 97:
			contents.addNumber("１");
			break;
		case 98:
			contents.addNumber("２");
			break;
		case 99:
			contents.addNumber("３");
			break;
		case 100:
			contents.addNumber("４");
			break;
		case 101:
			contents.addNumber("５");
			break;
		case 102:
			contents.addNumber("６");
			break;
		case 103:
			contents.addNumber("７");
			break;
		case 104:
			contents.addNumber("８");
			break;
		case 105:
			contents.addNumber("９");
			break;
		case 107:
			contents.addFunction("＋");
			break;
		case 109:
			contents.addFunction("－");
			break;
		case 111:
			contents.addFunction("÷");
			break;
		case 106:
			contents.addFunction("×");
			break;
		case 110:
			contents.addFunction("．");
			break;
		case 8:
			contents.addFunction("Bc");
			break;
		case 10:
			contents.addFunction("＝");
			break;
		case 127:
			contents.addFunction("Ｃ");
			break;
		}
		label1.setText(contents.getLabel1Value());
		if(contents.getLabel2Value() != "0")
			label2.setText(contents.getLabel2Value());
	}
	
	
	/****************************************
	 * 			未使用メソッド		 		*
	 ****************************************/
	@Override
	public void keyReleased(KeyEvent e) {
		// 未使用。
	}
	@Override
	public void keyTyped(KeyEvent e) {
		//未使用。
	}
}
