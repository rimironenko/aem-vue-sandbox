import queryString from 'query-string';

const state = {
  assets: [],
};

const mutations = {
  setAssets(state, assets) {
    state.assets = assets;
  },
};

const actions = {
  getAssets({ commit }, assetsPath) {
  	const params = {
      path: assetsPath,
    };

    const urlService = `/services/aem-vue-sandbox/assets?${queryString.stringify(params)}`;

	$.getJSON(urlService).done(function(assets) {
	    console.log("Vue.js DAM: get assets from AEM success");
        commit('setAssets', assets);
	}).fail(function(){
        console.log('ERROR on get assets from AEM instance ');
	});

  },
};

export default {
  state,
  mutations,
  actions,
};
