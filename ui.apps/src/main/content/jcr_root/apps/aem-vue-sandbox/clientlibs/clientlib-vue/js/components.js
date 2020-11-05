import counter from '../../../components/vue/counter/clientlib/js/counter.js'
import clock from '../../../components/vue/clock/clientlib/js/clock.js'
import assetslist from '../../../components/vue/assets-list/clientlib/js/assetslist.js'
import todolist from '../../../components/vue/todo-list/clientlib/js/todolist.js'

const components = [];

components.push(...[
    counter,
    clock,
    assetslist,
    todolist]);

export default components;