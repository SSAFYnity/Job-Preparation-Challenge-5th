function solution(record) {
    let user = new Map();
    a = record.map((v) => v.split(" "));
    console.log(a);
    
    
    record.forEach((info) => {
        const [state, id, nickname] = info.split(" ");
        if(nickname){
            user.set(id, nickname);    
        }
    })
    
    let result = [];
    
    record.forEach((info) => {
        const [state, id, nickname] = info.split(" ");
        if(state === "Enter"){
            result.push(user.get(id)+"님이 들어왔습니다.");
        }
        else if(state === "Leave"){
            result.push(user.get(id)+"님이 나갔습니다.");
        }
    })
    
    return result;
}