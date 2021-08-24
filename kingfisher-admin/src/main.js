// apollo imports
import ApolloClient from 'apollo-client';
import { HttpLink } from 'apollo-link-http';
import { InMemoryCache } from 'apollo-cache-inmemory';
import { IntrospectionFragmentMatcher } from 'apollo-cache-inmemory';

// import {fragmentMatcher} from './fragmentMatcher';
// import IntrospectionFragmentMatcher from 'apollo-client';
// import { ApolloProvider } from 'react-apollo';
import Vue from 'vue';
import VueApollo from 'vue-apollo';
import VueRouter from 'vue-router'
import VueQuillEditor from 'vue-quill-editor';
import 'quill/dist/quill.snow.css';
import Home from "./components/Home";
import SchemaList from "./components/SchemaList";
import ContentTypeDetail from "./components/ContentTypeDetail";
import CatalogDetail from "./components/CatalogDetail";
import CatalogList from "./components/CatalogList";
import ContentItemList from "./components/ContentItemList";
import ContentItemDetail from "./components/ContentItemDetail";
import SchemaDetail from "./components/SchemaDetail";
import SchemaCreate from "./components/SchemaCreate";

import App from './App';

Vue.config.productionTip = false;
// https://github.com/apollographql/apollo-client/issues/2303
const apolloFragmentMatcher = new IntrospectionFragmentMatcher({
  introspectionQueryResultData: {
    __schema: {
      types: [
        {
          kind: "UNION",
          name: "FieldValue",
          possibleTypes: [
            { name: "StringFieldValue" },
            { name: "LocalizedStringFieldValue" },
            { name: "TextFieldValue" },
          ],
        },
      ],
    },
  }
});


const apolloClient = new ApolloClient({
//  link: new HttpLink({ uri: 'http://192.168.178.75:8089/graphql'}),
  link: new HttpLink({ uri: 'http://localhost:8089/graphql'}),
  cache: new InMemoryCache({

    fragmentMatcher: apolloFragmentMatcher
  }),

  // cache: new InMemoryCache().restore(window.__APOLLO_STATE__)
});

Vue.use(VueRouter);
Vue.use(VueApollo);
Vue.use(VueQuillEditor);

const routes = [
  { 'path': '/', component: Home },
  { 'path': '/content', component: Home },
  { 'path': '/content/schemas', component: SchemaList },
  { 'path': '/content/schema/:schemaKey', component: SchemaDetail, props: true },
  { 'path': '/content/catalog/:schemaKey', component: CatalogList, props: true },
  { 'path': '/content/contenttype/:schemaKey/:contentTypeKey', component: ContentTypeDetail, props: true },
  { 'path': '/content/catalog/:schemaKey/:catalogKey', component: CatalogDetail, props: true },
  { 'path': '/content/contentitem/:schemaKey/:catalogKey/:catalogVersionKey', component: ContentItemList, props: true },
  { 'path': '/content/contentitem/:schemaKey/:catalogKey/:catalogVersionKey/:contentItemKey', component: ContentItemDetail, props: true },
  { 'path': '/foo', component: SchemaCreate }
];

const router = new VueRouter({
  routes: routes
});

// export default new Router({
//   routes: [
//     {
//       path: "/",
//       name: "app",
//       components: {
//         SchemaList,
//         SchemaCreate
//       }
//     }
//   ]
// });


const apolloProvider = new VueApollo({
  defaultClient: apolloClient,
  defaultOptions: {
    $loadingKey: 'loading'
  }
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router: router,
  apolloProvider,
  render: h => h(App)
});

/**
 * Returns the first non-blank value, '' otherwise.
 * Abbreviated to fnb because it will be used a lot.
 */
Vue.filter('fnb', function (value) {
  for (value of arguments) {
    if (value) {
      return value;
    }
  }
  return '';
});