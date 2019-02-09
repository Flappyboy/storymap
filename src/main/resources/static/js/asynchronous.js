var AsynExec = function (args) {
    if (!(this instanceof AsynExec)) {
        return new AsynExec(args);
    }

    this.start = args.start?args.start:function () {};
    this.funcStart = args.funcStart?args.funcStart:function () {};
    this.end = args.end?args.end:function () {};
    this.funcEnd = args.funcEnd?args.funcEnd:function () {};
    this.delayEnd = args.delayEnd?args.delayEnd:function () {};
    this.intervalTime = args.intervalTime? args.intervalTime:100;
    this.delayEndTime = args.delayEndTime? args.delayEndTime:1000;

    this._funcQueue=[];
    this._isRequest = false;
    this._busy = false;
    this._delayEndTimer=null;

    this._timer = setInterval(this._intervalFunc.bind(this), this.intervalTime);
}

AsynExec.prototype.pushFunc = function(func){
    this._funcQueue.push(func);
}
AsynExec.prototype._start = function () {
    if(!this._busy){
        if(this._delayEndTimer!=null){
            clearTimeout(this._delayEndTimer);
            this._delayEndTimer=null;
        }
        this._busy = true;
        this.start();
    }
}
AsynExec.prototype._end = function () {
    if(this._busy){
        this._busy = false;
        this.end();
        this._delayEndTimer = setTimeout(this._delayEnd.bind(this),this.delayEndTime);
    }
}
AsynExec.prototype._delayEnd = function () {
    this.delayEnd();
}

AsynExec.prototype._funcStart = function () {
    this._isRequest = true;
    this._start();
    this.funcStart();
}
AsynExec.prototype._funcEnd = function () {
    this.funcEnd();
    this._isRequest = false;
}
AsynExec.prototype._intervalFunc = function () {
    // console.log("isRequest %s", this._isRequest);
    if (!this._isRequest){
        if (this._funcQueue.length > 0) {
            this._funcStart();
            var func = this._funcQueue.shift();
            try {
                func(this._funcEnd.bind(this));
            } catch (e) {
                this._funcEnd();
                console.error(e);
            }
        }else{
            this._end();
        }
    }
}