(this["webpackJsonpgs-report-template-ui"]=this["webpackJsonpgs-report-template-ui"]||[]).push([[0],{67:function(e,t,r){},68:function(e,t,r){},90:function(e,t,r){"use strict";r.r(t);var a=r(1),n=r(34),c=r.n(n),s=(r(67),r(68),r(5)),l=r(3),i=r(15),o=r(0),u=["children"],d=function(e){var t=e.children,r=Object(i.a)(e,u);return Object(o.jsx)("div",{className:"m-2 w-full",children:Object(o.jsx)("div",Object(l.a)(Object(l.a)({className:"flex pr-4"},r),{},{children:t}))})},b={primary:"bg-black text-white hover:bg-gray-600",secondary:"bg-blue-600 text-white hover:bg-blue-400",tertiary:"bg-yellow-600 text-white hover:bg-yellow-400",error:"bg-red-600 text-white hover:bg-red-400"},j={xs:"h-10 w-28",s:"h-12 w-32"},p=function(e){var t=e.variant,r=void 0===t?"primary":t,a=e.size,n=void 0===a?"s":a,c=e.children,s=e.customStyle,l=void 0===s?"":s,i=e.disabled,u=void 0!==i&&i,d=e.className,p=e.onClick,x=void 0===p?function(){}:p;return Object(o.jsx)(o.Fragment,{children:u?Object(o.jsx)("button",{onClick:x,className:"bg-gray-500 text-white cursor-not-allowed ".concat(j[n]," ")+d,disabled:u,children:c}):Object(o.jsx)("button",{onClick:x,className:"".concat(b[r]," ").concat(j[n]," ").concat(l)+d,disabled:u,children:c})})},x=r(6),h=r(10),f=r(2),O=r.n(f),m="/api/template",v="/api/report",w=function(){var e=Object(x.a)(O.a.mark((function e(t){var r;return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("".concat(m),{method:"POST",body:JSON.stringify(t),headers:{"Content-Type":"application/json"}});case 2:return r=e.sent,e.abrupt("return",r.json());case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),g=function(){var e=Object(x.a)(O.a.mark((function e(){var t;return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("".concat(m),{method:"GET"});case 2:return t=e.sent,e.abrupt("return",t.json());case 4:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}(),y=function(){var e=Object(x.a)(O.a.mark((function e(t){var r;return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("".concat(m,"/").concat(t),{method:"GET"});case 2:return r=e.sent,e.abrupt("return",r);case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),N=function(){var e=Object(x.a)(O.a.mark((function e(t,r,a){var n,c;return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return(n=new FormData).append("file",t),r&&n.append("name",r),a&&n.append("description",a),e.next=6,fetch("".concat(m,"/upload"),{method:"POST",body:n});case 6:return c=e.sent,e.abrupt("return",c.json());case 8:case"end":return e.stop()}}),e)})));return function(t,r,a){return e.apply(this,arguments)}}(),k=function(){var e=Object(x.a)(O.a.mark((function e(t){var r;return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("".concat(m,"/").concat(t),{method:"DELETE"});case 2:return r=e.sent,e.abrupt("return",r.json());case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),C=function(){var e=Object(x.a)(O.a.mark((function e(t){var r;return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("".concat(m,"/download/").concat(t),{method:"POST"});case 2:return r=e.sent,e.abrupt("return",r);case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),S=function(){var e=Object(x.a)(O.a.mark((function e(t,r){var a;return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("".concat(v,"/generate/").concat(t),{method:"POST",body:r});case 2:return a=e.sent,e.abrupt("return",a);case 4:case"end":return e.stop()}}),e)})));return function(t,r){return e.apply(this,arguments)}}(),T=function(){var e=Object(x.a)(O.a.mark((function e(t,r){var a,n,c=arguments;return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return a=c.length>2&&void 0!==c[2]?c[2]:1,e.next=3,fetch("".concat(v,"/performance/generate/").concat(t,"/").concat(a),{method:"POST",body:r});case 3:return n=e.sent,e.abrupt("return",n.json());case 5:case"end":return e.stop()}}),e)})));return function(t,r){return e.apply(this,arguments)}}(),E=function(){var e=Object(x.a)(O.a.mark((function e(t){var r;return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("".concat(v,"/performance/").concat(t),{method:"GET"});case 2:return r=e.sent,e.abrupt("return",r.json());case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),D=["onChange","onKeyPress","value","label","placeholder","id","labelStyle","disabled","maxLength"],P=function(e){var t=e.onChange,r=e.onKeyPress,a=e.value,n=e.label,c=e.placeholder,s=e.id,u=e.labelStyle,d=e.disabled,b=e.maxLength,j=Object(i.a)(e,D);return Object(o.jsxs)("div",{className:"flex w-full mb-6 md:mb-0",children:[n&&Object(o.jsx)("label",{className:"block tracking-wide text-gray-700 text-lg m-2 text-right ".concat(u),htmlFor:s,children:n}),Object(o.jsx)("input",Object(l.a)({className:"appearance-none block w-full ".concat(d?"bg-gray-400":"bg-gray-200"," text-gray-700 border rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white"),id:s,type:"text",placeholder:c,value:a,onChange:t,onKeyPress:r,disabled:d,maxLength:b},j))]})},U=r(46),R=["XLS","XLSX"],L=function(e){var t=e.onDrop;return Object(o.jsx)(U.a,{classes:"h-full",handleChange:function(e){t(e)},name:"file",types:R})},J=function(e){var t,r=e.template,a=e.dispatchTemplate,n=e.save,c=e.doesNameExist,s=e.exist,i=e.isUpdate,u=function(){var e=Object(x.a)(O.a.mark((function e(t){var n;return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,N(t,r.name,r.description);case 2:n=e.sent,a({type:"JSON",payload:n});case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}();return Object(o.jsxs)("div",{className:"w-full p-4",children:[Object(o.jsxs)("div",{className:"mb-2 text-left",children:[Object(o.jsx)("text",{className:"text-lg font-bold",children:"Template Structure"}),Object(o.jsx)("text",{children:" - Start by uploading your excel file template"})]}),Object(o.jsx)("div",{className:"mb-6 w-full text-left",children:Object(o.jsx)(L,{onDrop:u})}),Object(o.jsx)("div",{className:"w-full max-w-lg",children:Object(o.jsxs)("div",{className:"flex flex-wrap -mx-3 mb-6",children:[Object(o.jsx)(P,{label:"*Name:",id:"name",onChange:function(e){var t,n=null===(t=e.target.value)||void 0===t?void 0:t.replace(" ","");a({type:"UPDATE",payload:Object(l.a)(Object(l.a)({},r),{},{name:n})}),n&&n.length>=6&&c(n)},value:r.name,maxLength:20,placeholder:"Enter template name without spaces (min 6 , max 20 char)",disabled:i}),s&&(null===(t=r.name)||void 0===t?void 0:t.length)>=6&&Object(o.jsxs)("span",{className:"flex flex-wrap m-1 text-red-400 text-sm",children:["Name ",r.name," already exist."]})]})}),Object(o.jsx)("div",{className:"w-full max-w-lg",children:Object(o.jsx)("div",{className:"flex flex-wrap -mx-3 mb-6",children:Object(o.jsx)(P,{label:"*Description:",id:"description",onChange:function(e){a({type:"UPDATE",payload:Object(l.a)(Object(l.a)({},r),{},{description:e.target.value})})},value:r.description,maxLength:300,placeholder:"Enter description (max 300 char)"})})}),Object(o.jsx)("div",{className:"w-full max-w-lg",children:Object(o.jsx)("div",{className:"flex flex-wrap -mx-3 mb-6",children:Object(o.jsx)(p,{disabled:!r.name||!r.description||r.name.length<6||s,onClick:function(){return n()},children:"Save"})})})]})},F=r(50),M=r(23),A=function(e){var t=e.template,r=void 0===t?{}:t,n=Object(a.useRef)(null);return Object(a.useEffect)((function(){n.current.innerHTML=F.a.toHtml(r)})),Object(o.jsxs)("div",{className:"flex-grow w-full text-left p-4 border-l-2 border-solid",children:[Object(o.jsx)("text",{className:"text-lg font-bold",children:"Preview"}),Object(o.jsx)("text",{children:" - This is a preview of your report"}),Object(o.jsx)("div",{className:"p-2",children:Object(o.jsx)(M.CopyToClipboard,{className:"bg-green-500 hover:cursor-pointer p-2 rounded-md",text:JSON.stringify(r),onCopy:function(){return alert("Copy to Clipboard was successful.")},children:Object(o.jsx)("span",{children:"Copy JSON to Clipboard"})})}),Object(o.jsx)("div",{className:"border-gray-400 border-solid",children:Object(o.jsx)("div",{className:"w-full text-left mt-2",children:Object(o.jsx)("pre",{className:"relative rounded-xl overflow-auto p-4",ref:n})})})]})},B="xlsx",H=r(24),_=r.n(H),G=function(){return Object(o.jsx)("div",{className:"relative flex py-5 items-center m-3",children:Object(o.jsx)("div",{className:"flex-grow border-t border-gray-200"})})},I={name:null,description:null,format:B,sheets:[],createdBy:"Jed Dayo"},W=function(e){return Object(l.a)({},e)},K="RESET",X=function(e,t){switch(t.type){case"UPDATE":return Object(l.a)(Object(l.a)({},e),t.payload);case"UPDATE_ROW":var r=t.payload,a=r.index,n=r.key,c=r.value,s=r.parent,i=Object(h.a)(e[s]);i[a][n]=c;var o=Object(l.a)({},e);return o[s]=i,Object(l.a)(Object(l.a)({},e),o);case"ADD_ROW":var u=t.payload,d=u.parent,b=u.defaultRow,j=Object(l.a)({},e);return j[d]=[].concat(Object(h.a)(e[d]),[b]),Object(l.a)({},j);case"DELETE_ROW":var p=t.payload,x=p.index,f=p.parent,O=Object(h.a)(e[f]),m=Object(l.a)({},e);return O.splice(x,1),m[f]=Object(h.a)(O),Object(l.a)({},m);case"MOVE_COLUMN_DOWN":var v=t.payload,w=v.index,g=v.parent,y=Object(h.a)(e[g]);y[w].index=y[w].index+1,y[w+1].index=y[w+1].index-1;var N=y[w];y[w]=y[w+1],y[w+1]=N;var k=Object(l.a)({},e);return k[g]=Object(h.a)(y),Object(l.a)({},k);case"MOVE_COLUMN_UP":var C=t.payload,S=C.index,T=C.parent,E=Object(h.a)(e[T]);E[S].index=E[S].index-1,E[S-1].index=E[S-1].index+1;var D=E[S];E[S]=E[S-1],E[S-1]=D;var P=Object(l.a)({},e);return P[T]=Object(h.a)(E),Object(l.a)({},P);case K:return Object(l.a)({},W(I));case"JSON":return Object(l.a)(Object(l.a)({},e),t.payload);default:throw new Error}},V=function(e){var t=e.back,r=e.existingTemplate,n=e.isUpdate,c=Object(a.useReducer)(X,r||I,W),l=Object(s.a)(c,2),i=l[0],u=l[1],d=Object(a.useState)(!1),b=Object(s.a)(d,2),j=b[0],h=b[1],f=Object(a.useState)(),m=Object(s.a)(f,2),v=m[0],g=m[1],N=function(){var e=Object(x.a)(O.a.mark((function e(t){return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,y(t);case 3:200===e.sent.status?g(!0):g(!1),e.next=10;break;case 7:e.prev=7,e.t0=e.catch(0),g(!1);case 10:case"end":return e.stop()}}),e,null,[[0,7]])})));return function(t){return e.apply(this,arguments)}}(),k=function(){var e=Object(x.a)(O.a.mark((function e(){return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,w(i);case 3:t(),u({type:K}),e.next=11;break;case 7:e.prev=7,e.t0=e.catch(0),console.error(e.t0),alert("Error saving the template. Unable to contact server.");case 11:case"end":return e.stop()}}),e,null,[[0,7]])})));return function(){return e.apply(this,arguments)}}(),S=function(){var e=Object(x.a)(O.a.mark((function e(){var t;return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:t=null,C(i.name).then((function(e){return 200===e.status?(t=e.headers.get("content-disposition").split(";")[1].split("=")[1],e.blob()):void 0})).then((function(e){_()(e,t,"application/octet-stream")}));case 2:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}();return Object(o.jsxs)("div",{className:"m-2 w-full h-full",children:[Object(o.jsx)(G,{}),Object(o.jsxs)("div",{className:"w-full h-full border-l border-gray-200 gap-2 flex justify-end",children:[n&&Object(o.jsx)(p,{variant:"tertiary",onClick:S,children:"Download Template"}),Object(o.jsx)(p,{variant:"tertiary",onClick:function(){return h(!j)},className:"mr-3",children:"".concat(j?"Close Preview":"Open Preview")})]}),Object(o.jsx)(G,{}),Object(o.jsxs)("div",{className:"m-2 flex w-full h-full",children:[Object(o.jsx)(J,{template:i,dispatchTemplate:u,save:k,doesNameExist:N,exist:v,isUpdate:n}),j&&Object(o.jsx)(A,{template:i})]})]})},q=r(51),z=r.n(q),Y=r(62),$=["title"],Q=function(e){var t=e.title,r=Object(i.a)(e,$);return Object(o.jsx)("div",Object(l.a)(Object(l.a)({className:"font-sans text-lg text-white bg-blue-400 p-2 flex"},r),{},{children:Object(o.jsx)("h2",{children:t})}))},Z=r(61),ee=function(e){var t=e.setHasError,r=e.setJson,n=e.template,c=Object(a.useState)({test:"excel"}),l=Object(s.a)(c,2),i=l[0],u=l[1],d=function(){var e=Object(x.a)(O.a.mark((function e(){var t;return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,E(n.name);case 2:if((t=e.sent)&&0!==t.length){e.next=6;break}return alert("No last sample data used."),e.abrupt("return");case 6:u(JSON.parse(t[0].data)),r(t[0].data);case 8:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}();return Object(o.jsxs)("div",{className:"p-2",children:[Object(o.jsx)("span",{className:"text-lg cursor-pointer underline text-blue-500",onClick:d,children:"use last sample"}),Object(o.jsx)(Z.a,{id:n.name,placeholder:i,height:"550px",width:"700px",onChange:function(e){var a=e.json;e.error&&t(!0),u(JSON.parse(a)),r(a)}})]})},te=function(e){var t=e.template,r=Object(a.useState)(),n=Object(s.a)(r,2),c=n[0],l=n[1],i=Object(a.useState)(!1),u=Object(s.a)(i,2),d=u[0],b=u[1],j=Object(a.useState)(!1),h=Object(s.a)(j,2),f=h[0],m=h[1],v=Object(a.useState)([]),w=Object(s.a)(v,2),g=w[0],y=w[1],N=function(){var e=Object(x.a)(O.a.mark((function e(){var r;return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,E(t.name);case 2:r=e.sent,y(r);case 4:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}();Object(a.useEffect)((function(){N()}),[]);var k=function(){var e=Object(x.a)(O.a.mark((function e(){var r,a;return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(r=prompt("How many reports? enter valid number",1),a=new RegExp("^[0-9]*$"),!(r&&a.test(r)&&r<=100)){e.next=8;break}return e.next=5,T(t.name,c,r);case 5:C(),e.next=9;break;case 8:alert("Not a valid count, max is 100");case 9:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}(),C=function(){m(!1),N()};return Object(o.jsxs)(o.Fragment,{children:[!f&&Object(o.jsxs)("div",{className:"m-2 w-full h-full",children:[Object(o.jsxs)("table",{className:"table-auto w-full text-sm",children:[Object(o.jsx)("thead",{children:Object(o.jsxs)("tr",{className:"border-b dark:border-slate-600 font-medium p-4 pl-8 pt-0 pb-3 text-slate-400 dark:text-slate-200 text-left",children:[Object(o.jsx)("th",{width:"20%",children:"Run Date"}),Object(o.jsx)("th",{width:"15%",children:"Count"}),Object(o.jsx)("th",{width:"10%",children:"Memory Consumption (bytes)"}),Object(o.jsx)("th",{width:"10%",children:"Memory Consumption (mb)"}),Object(o.jsx)("th",{width:"10%",children:"Execution time (ms)"}),Object(o.jsx)("th",{width:"20%"})]})}),Object(o.jsx)("tbody",{className:"bg-white dark:bg-slate-800",children:g&&g.map((function(e,t){return Object(o.jsxs)("tr",{className:"text-left",children:[Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:e.executedDateTime}),Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:e.reportCount}),Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:e.memoryInBytes}),Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:e.memoryInMB}),Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:e.elapsedTimeInMS}),Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:Object(o.jsx)("div",{className:"flex gap-2 space-x-8",children:Object(o.jsx)(M.CopyToClipboard,{className:"bg-green-500 hover:cursor-pointer p-2 rounded-md",text:e.data,onCopy:function(){return alert("Copy to Clipboard was successful.")},children:Object(o.jsx)("span",{children:"Copy test data"})})})})]},"row-".concat(t))}))})]}),Object(o.jsx)("div",{className:"p-2 flex gap-2",children:Object(o.jsx)(p,{variant:"tertiary",onClick:function(){return m(!0)},children:"Setup Test Run"})})]}),f&&Object(o.jsxs)(o.Fragment,{children:[Object(o.jsx)("div",{className:"p-2",children:Object(o.jsx)("div",{className:"p-2",children:Object(o.jsx)(ee,{setHasError:b,setJson:l,template:t})})}),Object(o.jsxs)("div",{className:"p-2 flex gap-2",children:[Object(o.jsx)(p,{variant:"tertiary",onClick:k,disabled:d,children:"Run Test"}),Object(o.jsx)(p,{variant:"tertiary",onClick:C,children:"Show History"})]})]})]})},re=function(e){var t=e.template,r=Object(a.useState)(),n=Object(s.a)(r,2),c=n[0],l=n[1],i=Object(a.useState)(!1),u=Object(s.a)(i,2),d=u[0],b=u[1],j=function(){var e=Object(x.a)(O.a.mark((function e(){var r;return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:r=null,S(t.name,c).then((function(e){return 200===e.status?(r=e.headers.get("content-disposition").split(";")[1].split("=")[1],e.blob()):void 0})).then((function(e){_()(e,r,"application/octet-stream")}));case 2:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}();return Object(o.jsxs)(o.Fragment,{children:[Object(o.jsx)("div",{className:"p-2",children:Object(o.jsx)("div",{className:"p-2",children:Object(o.jsx)(ee,{setHasError:b,setJson:l,template:t})})}),Object(o.jsx)("div",{className:"p-2 flex gap-2",children:Object(o.jsx)(p,{variant:"tertiary",onClick:j,disabled:d,children:"Generate Report"})})]})},ae={content:{top:"50%",left:"50%",right:"auto",bottom:"auto",marginRight:"-50%",transform:"translate(-50%, -50%)",width:"800px"}},ne=function(e){var t=e.template,r=e.isOpen,n=e.customStyles,c=void 0===n?ae:n,l=e.onClose,i=Object(a.useState)(!1),u=Object(s.a)(i,2),b=u[0],j=u[1];return Object(o.jsx)("div",{className:"w-full",children:Object(o.jsxs)(z.a,{isOpen:r,onRequestClose:l,style:c,children:[Object(o.jsx)("button",{onClick:l,className:"absolute top-0 right-0 text-xl",children:Object(o.jsx)(Y.a,{})}),Object(o.jsx)(Q,{title:"Test Your Template - ".concat(t.name)}),Object(o.jsx)("div",{className:"w-full",children:Object(o.jsx)(d,{children:Object(o.jsx)(p,{onClick:function(){return j(!b)},variant:"error",children:"".concat(b?"<< Back to Generator":"Show Performance >>")})})}),Object(o.jsx)(G,{}),b&&Object(o.jsx)(te,{template:t}),!b&&Object(o.jsx)(re,{template:t})]})})},ce=function(e){var t=e.handleUpdate,r=Object(a.useState)([]),n=Object(s.a)(r,2),c=n[0],l=n[1],i=Object(a.useState)(),u=Object(s.a)(i,2),d=u[0],b=u[1],j=function(){var e=Object(x.a)(O.a.mark((function e(){var t;return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,g();case 3:t=e.sent,l(t),e.next=11;break;case 7:e.prev=7,e.t0=e.catch(0),console.error(e.t0),alert("Error fetching templates. Unable to contact server.");case 11:case"end":return e.stop()}}),e,null,[[0,7]])})));return function(){return e.apply(this,arguments)}}();Object(a.useEffect)((function(){j()}),[]);var h=function(){var e=Object(x.a)(O.a.mark((function e(t){return O.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,k(t);case 3:alert("Delete was successful of template - ".concat(t,".")),j(),e.next=11;break;case 7:e.prev=7,e.t0=e.catch(0),console.error(e.t0),alert("Error fetching templates. Unable to contact server.");case 11:case"end":return e.stop()}}),e,null,[[0,7]])})));return function(t){return e.apply(this,arguments)}}();return Object(o.jsxs)("div",{className:"m-2 w-full h-full",children:[Object(o.jsxs)("table",{className:"table-auto w-full text-sm",children:[Object(o.jsx)("thead",{children:Object(o.jsxs)("tr",{className:"border-b dark:border-slate-600 font-medium p-4 pl-8 pt-0 pb-3 text-slate-400 dark:text-slate-200 text-left",children:[Object(o.jsx)("th",{width:"20%",children:"Name"}),Object(o.jsx)("th",{width:"15%",children:"Format"}),Object(o.jsx)("th",{width:"20%",children:"Description"}),Object(o.jsx)("th",{width:"10%",children:"Action"})]})}),Object(o.jsx)("tbody",{className:"bg-white dark:bg-slate-800",children:c&&c.map((function(e,r){return Object(o.jsxs)("tr",{className:"text-left",children:[Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:e.name}),Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:e.format}),Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:e.description}),Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:Object(o.jsxs)("div",{className:"flex gap-2 space-x-8",children:[Object(o.jsx)(p,{variant:"secondary",onClick:function(){return t(e)},children:"Update"}),Object(o.jsx)(p,{variant:"error",onClick:function(){return h(e.name)},children:"Delete"}),Object(o.jsx)(p,{variant:"tertiary",onClick:function(){return function(e){b(e)}(e)},children:"Test"})]})})]},"row-".concat(r))}))})]}),d&&Object(o.jsx)(ne,{template:d,isOpen:d,onClose:function(){return b(null)}})]})},se=function(){var e=Object(a.useState)(),t=Object(s.a)(e,2),r=t[0],n=t[1],c=Object(a.useState)(),l=Object(s.a)(c,2),i=l[0],u=l[1],b=Object(a.useState)(),j=Object(s.a)(b,2),x=j[0],h=j[1];return Object(o.jsxs)("div",{children:[Object(o.jsx)("div",{className:"w-full",children:Object(o.jsx)(d,{children:Object(o.jsx)(p,{onClick:function(){r?n(!1):(n(!0),h(!1),u(null))},variant:"secondary",children:"".concat(r?"Back to Dashboard":"Add Template")})})}),r&&Object(o.jsx)(V,{back:function(){return n(!1)},existingTemplate:i,isUpdate:x}),!r&&Object(o.jsx)(ce,{handleUpdate:function(e){u(e),n(!0),h(!0)}})]})},le=r(4),ie=r.p+"static/media/logo.dd0d67be.png",oe=function(e){var t=Object.assign({},e);return Object(o.jsxs)("div",Object(l.a)(Object(l.a)({className:"w-full flex bg-blue-500 text-white h-20"},t),{},{children:[Object(o.jsx)("img",{src:ie,className:"m-3"}),Object(o.jsx)("div",{className:"m-auto",children:"Template Report Maintenance"})]}))},ue=function(e){var t=e.children;return Object(o.jsx)("div",{className:"w-full h-full",children:t})},de=function(){var e=Object(le.f)();return Object(a.useEffect)((function(){e("/")}),[]),Object(o.jsxs)("div",{className:"App",children:[Object(o.jsx)(oe,{}),Object(o.jsx)(G,{}),Object(o.jsx)(ue,{children:Object(o.jsx)(le.c,{children:Object(o.jsx)(le.a,{exact:!0,path:"/",element:Object(o.jsx)(se,{})})})})]})},be=function(e){e&&e instanceof Function&&r.e(3).then(r.bind(null,91)).then((function(t){var r=t.getCLS,a=t.getFID,n=t.getFCP,c=t.getLCP,s=t.getTTFB;r(e),a(e),n(e),c(e),s(e)}))},je=r(21);r(40);c.a.render(Object(o.jsx)(je.a,{children:Object(o.jsx)(de,{})}),document.getElementById("root")),be()}},[[90,1,2]]]);
//# sourceMappingURL=main.2182b776.chunk.js.map