function solution(user_id, banned_id) {
    let userId = user_id;
    let bannedId = banned_id;
    
    let result = new Set();
    
    dfs(result, new Set(), 0, userId, bannedId);
    
    return result.size;
}

function dfs(result, set, depth, userId, bannedId){
    
    if(depth === bannedId.length){
        const uniqueSet = Array.from(set).sort().join(", ");
        result.add(uniqueSet);
        
        return;
    }
    
    
    for(let i = 0; i < userId.length; i++){
        if(set.has(userId[i])){
            continue;
        }
        
        if(check(userId[i], bannedId[depth])){
            set.add(userId[i]);
            dfs(result, new Set(set), depth + 1, userId, bannedId);
            set.delete(userId[i]);
        }
    }
}

function check(userId, bannedId){
    if(userId.length !== bannedId.length){
        return false;
    }
    
    for(let i = 0; i < userId.length; i++){
        if(bannedId.charAt(i) !== '*' && userId.charAt(i) !== bannedId.charAt(i)){
            return false;
        }
    }
    return true;
}