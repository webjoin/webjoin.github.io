
var dailyColumns=["date","category","subCategory","goods","amount","currency","drcr","payer","memo"];


var dailyDatas = [
    
    
    {"date":"2023-07-18","category":"消费","subCategory":"水果","goods":"苹果","amount":"14.35",currency:"AED",drcr:"D","payer":"Elijah","memo":"温超水果"},
    {"date":"2023-07-18","category":"消费","subCategory":"日常&菜蔬","goods":"温超晚餐","amount":"30.31",currency:"AED",drcr:"D","payer":"Elijah","memo":"温超菜蔬&熟食晚餐"},
    {"date":"2023-07-17","category":"消费","subCategory":"交通","goods":"打车下班","amount":"5.00",currency:"AED",drcr:"D","payer":"Elijah","memo":"和3同事拼车AA"},
    {"date":"2023-07-17","category":"消费","subCategory":"其他","goods":"xxx","amount":"12.00",currency:"AED",drcr:"D","payer":"Elijah","memo":""},
    {"date":"2023-07-17","category":"消费","subCategory":"其他","goods":"xxx","amount":"12.00",currency:"AED",drcr:"D","payer":"Elijah","memo":""},
    {"date":"2023-07-17","category":"消费","subCategory":"日常&菜蔬","goods":"Seven Colors","amount":"19.73",currency:"AED",drcr:"D","payer":"Elijah","memo":""},
    {"date":"2023-07-17","category":"消费","subCategory":"交通","goods":"小结打车费","amount":"72.00",currency:"AED",drcr:"D","payer":"Elijah","memo":""},
    {"date":"2023-07-17","category":"消费","subCategory":"交通","goods":"水果","amount":"2.00",currency:"AED",drcr:"D","payer":"Elijah","memo":"west zone(香蕉)"},

    
    {"date":"2023-07-11","category":"消费","subCategory":"交通","goods":"打车下班","amount":"5.50",currency:"AED",drcr:"D","payer":"Elijah","memo":"和3同事拼车AA"},
    {"date":"2023-07-09","category":"消费","subCategory":"医疗","goods":"洗牙&根管治疗&补牙","amount":"438.60",currency:"AED",drcr:"D","payer":"Elijah","memo":"With Jing"},
    {"date":"2023-07-08","category":"消费","subCategory":"日常&菜蔬","goods":"周末菜&油","amount":"109.46",currency:"AED",drcr:"D","payer":"Elijah","memo":"周末shopping"},
    {"date":"2023-07-08","category":"消费","subCategory":"日常特殊消费","goods":"配置眼镜","amount":"1500.00",currency:"AED",drcr:"C","payer":"Elijah","memo":"保险退还1500"},
    {"date":"2023-07-08","category":"消费","subCategory":"水果","goods":"Banana Philipine","amount":"5.29",currency:"AED",drcr:"D","payer":"Elijah","memo":"周末shopping"},
    {"date":"2023-07-06","category":"消费","subCategory":"日常&菜蔬","goods":"购买seabream一条","amount":"15.00",currency:"AED",drcr:"D","payer":"Elijah","memo":"晚餐煎鱼家常做法"},

    {"date":"2023-07-03","category":"消费","subCategory":"日常&菜蔬","goods":"鸡蛋","amount":"13.50",currency:"AED",drcr:"D","payer":"Elijah","memo":"在WEST ZONE沟买了鸡蛋"},
    {"date":"2023-07-03","category":"消费","subCategory":"交通","goods":"打车去公司","amount":"12.00",currency:"AED",drcr:"D","payer":"Elijah","memo":"从cleveland clinic 打车去公司"},
    {"date":"2023-07-03","category":"消费","subCategory":"医疗","goods":"挂号费","amount":"50.00",currency:"AED",drcr:"D","payer":"Elijah","memo":"General Surg 挂号费"},
    {"date":"2023-07-03","category":"消费","subCategory":"医疗","goods":"挂号费","amount":"50.00",currency:"AED",drcr:"D","payer":"Elijah","memo":"Primary Care 挂号费"},
    {"date":"2023-07-02","category":"消费","subCategory":"晚餐","goods":"Shawma","amount":"8.00",currency:"AED",drcr:"D","payer":"Elijah","memo":"Jing's Dinner"},
    {"date":"2023-07-02","category":"消费","subCategory":"医疗","goods":"Check&XRay Fees","amount":"92.00",currency:"AED",drcr:"D","payer":"Elijah","memo":"Elijah&Jing's Fees"},
    {"date":"2023-07-02","category":"消费","subCategory":"日常&菜蔬","goods":"Wemart 菜蔬&水果","amount":"83.00",currency:"AED",drcr:"D","payer":"Elijah","memo":"菜蔬&水果"},
    {"date":"2023-07-02","category":"消费","subCategory":"聚餐","goods":"Costa Coffee","amount":"43.00",currency:"AED",drcr:"-","payer":"Beili","memo":"和beili&xiaolong交通"},
    {"date":"2023-07-02","category":"消费","subCategory":"聚餐","goods":"Shanghai restaurant","amount":"201.00",currency:"AED",drcr:"D","payer":"Elijah","memo":"和beili&xiaolong交通"},
    
    {"date":"2023-06-30","category":"消费","subCategory":"日常消费","goods":"剪头发","amount":"15.00",currency:"AED",drcr:"D","payer":"Elijah","memo":"剪头发"},
    {"date":"2023-06-30","category":"消费","subCategory":"日常特殊消费","goods":"配置眼镜","amount":"1988.00",currency:"AED",drcr:"D","payer":"Elijah","memo":"使用保险可以cover80%顺便给Jing买了太阳镜"},
    {"date":"2023-06-29","category":"聚餐","subCategory":"聚餐","goods":"龙宝宝餐厅外卖","amount":"227.85",currency:"AED",drcr:"D","payer":"Elijah","memo":"探望LiLi姊妹"},
    {"date":"2023-06-29","category":"聚餐","subCategory":"聚餐","goods":"探望礼物","amount":"60.33",currency:"AED",drcr:"D","payer":"Elijah","memo":"探望LiLi姊妹的水果"},
    {"date":"2023-06-29","category":"聚餐","subCategory":"聚餐","goods":"Vietnam restaurant","amount":"224.70",currency:"AED",drcr:"D","payer":"Elijah","memo":"和Jizhen Huang弟兄聚餐交通分享（关于考研）"},
    {"date":"2023-06-29","category":"聚餐","subCategory":"聚餐","goods":"Baraba raha","amount":"128.25",currency:"AED",drcr:"D","payer":"Elijah","memo":"和Eric&siying聚餐交通 一天的交通"},
    {"date":"2023-06-29","category":"聚餐","subCategory":"聚餐","goods":"Off The Hook","amount":"185.00",currency:"AED",drcr:"-","payer":"Eric","memo":"和Eric&siying聚餐交通"},
    {"date":"2023-06-26","category":"新家","subCategory":"家具","goods":"床桌椅","amount":"2370.00",currency:"AED",drcr:"D","payer":"Elijah","memo":"从思思家购买家具"},
    {"date":"2023-06-26","category":"聚餐","subCategory":"聚餐","goods":"Qasr Alasala mandi and mathbi restaurant","amount":"165.00",currency:"AED",drcr:"-","payer":"BaoLiangLu","memo":"With BaoLiangLu"},
    {"date":"2023-06-25","category":"聚餐","subCategory":"聚餐","goods":"乾州火锅（烤肉）","amount":"390.00",currency:"AED",drcr:"D","payer":"Elijah","memo":"With罗老师金晓姐小聚"},
    {"date":"2023-06-24","category":"聚餐","subCategory":"礼物","goods":"粽子","amount":"84.00",currency:"AED",drcr:"D","payer":"Jing","memo":"麦子团契礼物"},
    {"date":"2023-06-24","category":"聚餐","subCategory":"聚餐","goods":"赢记（迪拜餐厅）","amount":"250.00",currency:"AED",drcr:"D","payer":"Jing","memo":"麦子团契聚餐AA制"},

    {"date":"2023-05-20","category":"婚礼","subCategory":"家人的机票","goods":"Jing's妈妈机票四川单程迪拜","amount":"4810.00",currency:"CNY",drcr:"D","payer":"Elijah","memo":"机票"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"家人的机票","goods":"Jing's妈妈机票四川往返迪拜","amount":"11414.00",currency:"CNY",drcr:"D","payer":"Elijah","memo":"机票"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"家人的机票","goods":"Elijah's爸爸哥哥机票武汉往返迪拜","amount":"8798.00",currency:"CNY",drcr:"D","payer":"Elijah","memo":"机票"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"家人的机票","goods":"Elijah's嫂子机票武汉往返迪拜","amount":"5765.00",currency:"CNY",drcr:"D","payer":"Elijah","memo":"机票"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"家人酒店","goods":"家人酒店","amount":"2925.00",currency:"AED",drcr:"D","payer":"Elijah","memo":"家人酒店"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"家人旅行观光","goods":"Louvre","amount":"240.00",currency:"AED",drcr:"D","payer":"Elijah","memo":"Elijah's爸爸&嫂子&Jing's妈妈"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"家人旅行观光","goods":"Sea World","amount":"1240.00",currency:"CNY",drcr:"D","payer":"Jing","memo":"Elijah's爸爸&嫂子&Jing's妈妈"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"家人旅行观光","goods":"Ferrari Park","amount":"540.00",currency:"AED",drcr:"D","payer":"Jing","memo":"Elijah's Bro&Jing's Bro"},

    {"date":"2023-05-20","category":"婚礼","subCategory":"婚礼","goods":"婚礼场地的装饰","amount":"3200.00",currency:"AED",drcr:"D","payer":"Jing","memo":"婚礼"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"婚礼","goods":"专业摄影","amount":"2500.00",currency:"AED",drcr:"D","payer":"Jing","memo":"婚礼"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"婚礼","goods":"酒席餐费","amount":"18200.00",currency:"AED",drcr:"D","payer":"Jing","memo":"婚礼"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"婚礼","goods":"钻戒指5000迪","amount":"5000.00",currency:"AED",drcr:"D","payer":"Jing","memo":"婚礼"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"婚礼","goods":"对戒2800迪","amount":"2800.00",currency:"AED",drcr:"D","payer":"Jing","memo":"婚礼"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"婚礼","goods":"婚纱","amount":"1400.00",currency:"AED",drcr:"D","payer":"Jing","memo":"婚礼"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"婚礼","goods":"淘宝买的小物品和礼品","amount":"3305.00",currency:"CNY",drcr:"D","payer":"Jing","memo":"婚礼"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"婚礼","goods":"空运费","amount":"305.00",currency:"CNY",drcr:"D","payer":"Jing","memo":"婚礼"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"婚礼","goods":"主内小礼品","amount":"424.00",currency:"CNY",drcr:"D","payer":"Jing","memo":"婚礼"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"礼金","goods":"Elijah's爸爸给彩礼","amount":"60000.00",currency:"CNY",drcr:"-","payer":"Elijah's爸爸","memo":"彩礼"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"礼金","goods":"Elijah's爸爸给Jing","amount":"12000.00",currency:"CNY",drcr:"C","payer":"Elijah's爸爸","memo":"礼金"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"礼金","goods":"Elijah's Bro礼金","amount":"10000.00",currency:"CNY",drcr:"-","payer":"Elijah's Bro","memo":"礼金（弟兄之间不需要送 最后被Jing‘s妈妈收下了）"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"礼金","goods":"Jing's朋友送的礼金","amount":"26620.00",currency:"CNY",drcr:"C","payer":"Jing's friends","memo":"礼金"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"礼金","goods":"Elijah's朋友送的礼金","amount":"12176.00",currency:"CNY",drcr:"C","payer":"Elijah's friends","memo":"礼金"},
    {"date":"2023-05-20","category":"婚礼","subCategory":"礼金","goods":"婚礼dxzm和朋友们的祝福","amount":"32500.00",currency:"AED",drcr:"C","payer":"Our friends","memo":"礼金"}
]

