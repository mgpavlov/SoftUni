class Player {
    constructor(nickName) {
        this.nickName = nickName;
        this.scoreList = [];
    }

    addScore(score) {
        if(!isNaN(score) && score !== null){ //If passed argument is not a valid number or a string representation of a number, do nothing (ignore it).
            this.scoreList.push(+score);
            this.scoreList.sort((a,b) => b-a);
        }

        return this;
    }

    get scoreCount() {//Accessor property
        return this.scoreList.length;
    }

    get highestScore() {//Accessor property
        return this.scoreList[0];
    }

    get topFiveScore() {//Accessor property
        return this.scoreList.slice(0, 5);
    }

    toString() {
        return `${this.nickName}: [${this.scoreList}]`;
    }
}