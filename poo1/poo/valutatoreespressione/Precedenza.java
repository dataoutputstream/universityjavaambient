package valutatoreespressione;

import java.util.Comparator;

class Precedenza implements Comparator<Character>{

	public int compare(Character op1, Character op2) {
		if(op2==op1){
			return 0;
		}else if(op1=='^'&& op2!='^'){
			return 1;
		}else if(op2=='^' && op1!='^'){
			return -1;
		}else if((op1=='*'||op1=='/'||op1=='%') && (op2=='*'||op2=='/'||op2=='%')){
			return 0;
		}else if((op1=='*'||op1=='/'||op1=='%') && ((op2!='*'||op2!='/'||op2!='%'))){
			return 1;
		}else if(((op1!='*'||op1!='/'||op1!='%')) && ((op2=='*'||op2=='/'||op2=='%'))){
			return -1;
		}return 0;
}
}

