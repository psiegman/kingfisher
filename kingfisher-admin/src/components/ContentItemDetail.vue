<template>
<div>
<ul class="breadcrumbs">
  <li class="breadcrumb">
    <router-link :to="'/content/schema/' + schemaKey">{{ schemaKey }}</router-link>
  </li>
  <li class="breadcrumb">
    <router-link :to="'/content/catalog/' + schemaKey + '/' + catalogKey">{{ catalogKey }}</router-link>
  </li>
  <li class="breadcrumb">
    <router-link :to="'/content/contentitem/' + schemaKey + '/' + catalogKey + '/' + catalogVersionKey">{{ catalogVersionKey }}</router-link>
  </li>
</ul>
<div v-if="schema">
<svg width="80" height="80" :data-jdenticon-value="schema.catalog.key"></svg>
<h1>ContentItem {{ schema.catalog.catalogVersion.contentItem.key }}</h1>
Key: {{ schema.catalog.catalogVersion.contentItem.key }}<br/>
Name: {{ schema.catalog.catalogVersion.contentItem.name }}<br/>
Type: <router-link :to="'/content/contenttype/' + schemaKey + '/' + schema.catalog.catalogVersion.contentItem.contentType.key">{{ schema.catalog.catalogVersion.contentItem.contentType.key }}</router-link>
<br/>
<p/>
    <form>
    message:
      <div class="form-group" v-for="(value, valueIndex) in message.values" v-bind:key="valueIndex">
        <input type="text" class="form-control" v-model="message.values[valueIndex]"/>
      </div>
    key:  <input type="text" class="form-control" v-model="schema.catalog.catalogVersion.contentItem.key"/>
    <div class="form-group" v-for="(field, fieldIndex) in schema.catalog.catalogVersion.contentItem.fields" v-bind:key="field.key">
      <label :for="field.key">{{ field.key}}</label>
      <input type="text" class="form-control" v-model="schema.catalog.catalogVersion.contentItem.fields[fieldIndex].value.content"/>
    </div>
    <div class="form-group">
        <button @click="saveContentItem()">Save</button>
    </div>
    </form>
</div>
</div>
</template>

<script>
import jdenticon from 'jdenticon';
// import Quill from 'quill';
// import VueQuillEditor from 'vue-quill-editor';
// import { quillEditor } from 'vue-quill-editor';
import {CONTENT_ITEM_DETAIL_QUERY, CONTENT_ITEM_UPDATE_MUTATION } from "../constants/graphql";

export default {
  name: "ContentItemDetail",
  props: ["schemaKey", "catalogKey", "catalogVersionKey" ,"contentItemKey"],
  data() {
    return {
      message: {
        values: ["foo", "bar"],
      },
      schema: null,
      loading: 0
    };
  },
  methods: {
    saveContentItem() {
      var fieldInputList = [];
//  updateContentItem(input: {contentItemKey: "article.about", catalogVersionKey: "draft", catalogKey: "admin", schemaKey: "_kingfisher", fields: [{fieldKey: "author", fieldValue: "me"}]}) {

      for (var field of this.schema.catalog.catalogVersion.contentItem.fields) {
        fieldInputList.push({
          fieldKey: field.key, fieldValue: field.value.content
        });
      }
      console.log(fieldInputList);
      // const {schemaKey,
      //     catalogKey,
      //     catalogVersionKey,
      //     contentItemKey,
      //     fields } = {schema,catalogKey,catalogVersionKey,contentItemKey,fields};
      this.$apollo.mutate({
        mutation: CONTENT_ITEM_UPDATE_MUTATION,
        variables: {
          schemaKey: this.$data.schema.key,
          catalogKey: this.$data.schema.catalog.key,
          catalogVersionKey: this.$data.schema.catalog.catalogVersion.key,
          contentItemKey: this.$data.schema.catalog.catalogVersion.contentItem.key,
          fields: fieldInputList
        }
      });
    }
  },
  mounted: function() {
    jdenticon();
  },
  updated: function() {
    jdenticon();
  },
  apollo: {
    schema: {
      query: CONTENT_ITEM_DETAIL_QUERY,
      variables() {
        return {
          schemaKey: this.schemaKey,
          catalogKey: this.catalogKey,
          catalogVersionKey: this.catalogVersionKey,
          contentItemKey: this.contentItemKey
        }
      },
      update: data => JSON.parse(JSON.stringify(data.cms.cmsContent.schema)),
    }
  }
};
</script>
