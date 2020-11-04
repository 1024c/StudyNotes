# Vue.js

Vue 是一套用于构建用户界面的渐进性式框架.

## 基本数据渲染和指令

- `v-` 前缀指令
- `{{}}` 插值表达式

| 指令      | 解释         | 示例                                                       |
| --------- | ------------ | ---------------------------------------------------------- |
| `v-bind`  | 单向数据绑定 | `v-bind:title='title'`                                     |
|           |              | 简写: `:title='title'`                                     |
| `v-model` | 双向数据绑定 | `v-model='value'`                                          |
| `v-on`    | 事件绑定     | `v-on:click='click()'`                                     |
|           |              | 简写: `@click='click'`                                     |
| `.`       | 修饰符       | `.prevent` 阻止默认行为                                    |
| `v-if`    | 条件指令     | `v-if='true'` 满足条件该组件即显示                         |
| `v-for`   | 循环指令     | `v-for='item in items'` 从 `items` 中依次遍历, 得到 `item` |

## 生命周期

1. `beforeCreate`        模板还未在内存中创建, 此时 `data`, `methods` 中的数据还未被初始化
1. `created`             模板已在内存中创建, 此时可以访问 `data`, `methods` 中的数据, 但是页面上还未挂载, 所以类似 `{{ }}` 插值表达式还未被渲染
1. `beforeMount`
1. `mounted`             模板已挂载
1. `beforeUpdate`        模板更新前触发, 获取的是还未更新的内容
1. `updated`             模板更新后触发, 获取的是更新了的内容
1. `beforeDestroy`
1. `destroyed`

## 路由

路由可以实现单页面多视图的功能, `<router-link>` 默认会被渲染成 `<a>` 标签

需要引入 vue-router.min.js 包

```html
<div id="app">
    <h1>Hello App!</h1>
    <p>
    <!-- 使用 router-link 组件来导航. -->
    <!-- 通过传入 `to` 属性指定链接. -->
    <!-- <router-link> 默认会被渲染成一个 `<a>` 标签 -->
    <router-link to="/">首页</router-link>
    <router-link to="/student">会员管理</router-link>
    </p>
    <!-- 路由出口 -->
    <!-- 路由匹配到的组件将渲染在这里 -->
    <router-view></router-view>
</div>
```

----------------------------------------------------------------

-------------------------Vue 结束 ------------------------------

----------------------------------------------------------------

## axios

axios 用于发送 `ajax` 请求:

引入 axios.min.js 包

```javascript
axios
    .get(url)
    .then(response => {
        ...
    })
    .catch(error => {
        ...
    })
```

## element-ui

饿了么前端基于 Vue.js 的组件库
