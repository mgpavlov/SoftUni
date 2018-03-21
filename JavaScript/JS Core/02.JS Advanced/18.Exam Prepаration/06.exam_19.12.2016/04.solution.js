function listBuilder(selector) {
    function createNewList() {
        let ul = $("<ul>");
        $(selector).empty();
        $(selector).append(ul);
    }
    function addItem(text) {
        let li = $("<li>").text(text);
        li.append($("<button>Up</button>").click(function () {
            let li = $(this).parent();
            li.insertBefore(li.prev());
        }));
        li.append($("<button>Down</button>").click(function () {
            let li = $(this).parent();
            li.insertAfter(li.next());
        }));
        $(selector + " ul").append(li);
    }

    return {
        createNewList,
        addItem
    }
}