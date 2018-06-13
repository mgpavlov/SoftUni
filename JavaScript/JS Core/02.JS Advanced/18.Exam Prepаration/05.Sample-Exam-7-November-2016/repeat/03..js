class TrainingCourse {
    constructor(title, trainer) {
        this.title = title;
        this.trainer = trainer;
        this.topics = [];
    }

    addTopic(topicTitle, date) {
        let newTopic =
            {
                topic: topicTitle,
                date: date
            };
        this.topics.push(newTopic);
    }

    firstTopic() {
        return Array.from(this.topics.map(a => a.date)).sort((a, b) => a > b)[0];
    }

    lastTopic() {
        return Array.from(this.topics.map(a => a.date)).sort((a, b) => a < b)[0];
    }

    toString() {
        Array.from(this.topics.map(a => a.date)).sort((a, b) => a > b)
        let result = `Course "${this.title}" by ${this.trainer}\n`;
        if(this.topics.length > 0){
            for(let topic of this.topics) {
                result += ` * ${topic.title} - ${topic.date.toString()}\n`
            }

            return result.trim();
        }

        return result;
    }
}
