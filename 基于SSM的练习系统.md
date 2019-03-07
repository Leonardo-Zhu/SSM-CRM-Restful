# 基于SSM的练习系统 v2.0

### 一、关于系统

#### （1）全局CSS样式 

> [Bootstrap 3.3.7](https://v3.bootcss.com/)

`<script src="https://cdn.bootcss.com/twitter-bootstrap/3.3.7/js/bootstrap.js"</script>`

#### （2）分页查询插件

##### 1、逻辑分页

> [dataTables](https://datatables.net "dataTables")

`<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>`

`<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>`

```javascript
<script>
    $(document).ready(function () {
        $("#custTable").DataTable({
            "ordering": false,
            "searching":false,
            "lengthChange":false,
            // "pageLength":20,
            language: {
                "lengthMenu": '每页显示 <select class="form-control">' + '<option value="10">10</option>'
                    + '<option value="20">20</option>'
                    + '<option value="30">30</option>'
                    + '<option value="40">40</option>'
                    + '<option value="50">50</option>' + '</select> 条',
                "paginate": {
                    "first": "首页",
                    "last": "尾页",
                    "previous": "上一页",
                    "next": "下一页"
                },
                "processing": "加载中...",  //DataTables载入数据时，是否显示‘进度’提示
                "emptyTable": "暂无数据",  //表格中无数据
                "info": "共 _PAGES_ 页  _TOTAL_ 条数据  ",
                "infoEmpty": "暂无数据",

                "search": "搜索:",
                "infoFiltered": " —— 从  _MAX_ 条数据中筛选",
                "zeroRecords": "没有找到记录"
            },
            ajax: "../customer/list",
            columns: [
                {"data": "custId","sClass": "text-center"},
                {"data": "custName","sClass": "text-center"},
                {"data": "custSource","sClass": "text-center"},
                {"data": "custIndustry","sClass": "text-center"},
                {"data": "custLevel","sClass": "text-center"},
                {"data": "custPhone","sClass": "text-center"},
                {"data": "custMobile","sClass": "text-center"}
            ]
        });
    });
</script>
```

##### 2、物理分页

> [PageHelper](https://pagehelper.github.io/)

```xml
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper</artifactId>
    <version>5.1.2</version>
</dependency>
```

```java
@RequestMapping("/customer/physical/{page}")
public String customer(@PathVariable("page") Integer page, QueryVo vo, Model model) {
    if (page == null) {
        page = 1;
    }
    PageHelper.startPage(page, 15);
    List<Customer> customerList = customerService.findCustomerByVo(vo).get("data");
    PageInfo<Customer> pageInfo = new PageInfo<>(customerList);

    model.addAttribute("pageInfo", pageInfo);
    model.addAttribute("customerList", customerList);
    
    return "system/cust_physical";
}
```

#### （3）商城数据库ER图

![](https://github.com/Leonardo-Zhu/SSM-CRM-Restful/blob/master/doc/er-schemas.png)











------

### 二、遇到的问题

1. Web网页路径问题
2. 静态资源无法加载
3. @SessionAttributes与@ResponseBody冲突无法共用