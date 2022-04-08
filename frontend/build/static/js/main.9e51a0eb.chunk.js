(this["webpackJsonpgs-report-template-ui"]=this["webpackJsonpgs-report-template-ui"]||[]).push([[0],{67:function(e,t,n){},68:function(e,t,n){},90:function(e,t,n){"use strict";n.r(t);var r=n(1),a=n(34),c=n.n(a),s=(n(67),n(68),n(5)),l=n(3),i=n(15),o=n(0),d=["children"],u=function(e){var t=e.children,n=Object(i.a)(e,d);return Object(o.jsx)("div",{className:"m-2 w-full",children:Object(o.jsx)("div",Object(l.a)(Object(l.a)({className:"flex pr-4"},n),{},{children:t}))})},j={primary:"bg-black text-white hover:bg-gray-600",secondary:"bg-blue-600 text-white hover:bg-blue-400",tertiary:"bg-yellow-600 text-white hover:bg-yellow-400",error:"bg-red-600 text-white hover:bg-red-400"},b={xs:"h-10 w-28",s:"h-12 w-32"},p=function(e){var t=e.variant,n=void 0===t?"primary":t,r=e.size,a=void 0===r?"s":r,c=e.children,s=e.customStyle,l=void 0===s?"":s,i=e.disabled,d=void 0!==i&&i,u=e.className,p=e.onClick,h=void 0===p?function(){}:p;return Object(o.jsx)(o.Fragment,{children:d?Object(o.jsx)("button",{onClick:h,className:"bg-gray-500 text-white cursor-not-allowed ".concat(b[a]," ")+u,disabled:d,children:c}):Object(o.jsx)("button",{onClick:h,className:"".concat(j[n]," ").concat(b[a]," ").concat(l)+u,disabled:d,children:c})})},h=n(6),x=n(10),m=n(2),f=n.n(m),O="/api/template",v="/api/report",g=function(){var e=Object(h.a)(f.a.mark((function e(t){var n;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("".concat(O),{method:"POST",body:JSON.stringify(t),headers:{"Content-Type":"application/json"}});case 2:return n=e.sent,e.abrupt("return",n.json());case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),w=function(){var e=Object(h.a)(f.a.mark((function e(){var t;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("".concat(O),{method:"GET"});case 2:return t=e.sent,e.abrupt("return",t.json());case 4:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}(),y=function(){var e=Object(h.a)(f.a.mark((function e(t){var n;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("".concat(O,"/").concat(t),{method:"GET"});case 2:return n=e.sent,e.abrupt("return",n);case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),N=function(){var e=Object(h.a)(f.a.mark((function e(t,n,r){var a,c;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return(a=new FormData).append("file",t),n&&a.append("name",n),r&&a.append("description",r),e.next=6,fetch("".concat(O,"/upload"),{method:"POST",body:a});case 6:return c=e.sent,e.abrupt("return",c.json());case 8:case"end":return e.stop()}}),e)})));return function(t,n,r){return e.apply(this,arguments)}}(),k=function(){var e=Object(h.a)(f.a.mark((function e(t){var n;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("".concat(O,"/").concat(t),{method:"DELETE"});case 2:return n=e.sent,e.abrupt("return",n.json());case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),C=function(){var e=Object(h.a)(f.a.mark((function e(t){var n;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("".concat(O,"/download/").concat(t),{method:"POST"});case 2:return n=e.sent,e.abrupt("return",n);case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),E=function(){var e=Object(h.a)(f.a.mark((function e(t,n){var r;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("".concat(v,"/generate/").concat(t),{method:"POST",body:n});case 2:return r=e.sent,e.abrupt("return",r);case 4:case"end":return e.stop()}}),e)})));return function(t,n){return e.apply(this,arguments)}}(),S=function(){var e=Object(h.a)(f.a.mark((function e(t,n){var r,a,c=arguments;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return r=c.length>2&&void 0!==c[2]?c[2]:1,e.next=3,fetch("".concat(v,"/performance/generate/").concat(t,"/").concat(r),{method:"POST",body:n});case 3:return a=e.sent,e.abrupt("return",a.json());case 5:case"end":return e.stop()}}),e)})));return function(t,n){return e.apply(this,arguments)}}(),T=function(){var e=Object(h.a)(f.a.mark((function e(t){var n;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,fetch("".concat(v,"/performance/").concat(t),{method:"GET"});case 2:return n=e.sent,e.abrupt("return",n.json());case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),D=["onChange","onKeyPress","value","label","placeholder","id","labelStyle","disabled","maxLength"],P=function(e){var t=e.onChange,n=e.onKeyPress,r=e.value,a=e.label,c=e.placeholder,s=e.id,d=e.labelStyle,u=e.disabled,j=e.maxLength,b=Object(i.a)(e,D);return Object(o.jsxs)("div",{className:"flex w-full mb-6 md:mb-0",children:[a&&Object(o.jsx)("label",{className:"block tracking-wide text-gray-700 text-lg m-2 text-right ".concat(d),htmlFor:s,children:a}),Object(o.jsx)("input",Object(l.a)({className:"appearance-none block w-full ".concat(u?"bg-gray-400":"bg-gray-200"," text-gray-700 border rounded py-3 px-4 mb-3 leading-tight focus:outline-none focus:bg-white"),id:s,type:"text",placeholder:c,value:r,onChange:t,onKeyPress:n,disabled:u,maxLength:j},b))]})},F=n(46),U=["XLS","XLSX"],L=function(e){var t=e.onDrop;return Object(o.jsx)(F.a,{classes:"h-full",handleChange:function(e){t(e)},name:"file",types:U})},R=function(e){var t,n=e.template,r=e.dispatchTemplate,a=e.save,c=e.doesNameExist,s=e.exist,i=e.isUpdate,d=function(){var e=Object(h.a)(f.a.mark((function e(t){var a;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,N(t,n.name,n.description);case 2:a=e.sent,r({type:"JSON",payload:a});case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}();return Object(o.jsxs)("div",{className:"w-full p-4",children:[Object(o.jsxs)("div",{className:"mb-2 text-left",children:[Object(o.jsx)("text",{className:"text-lg font-bold",children:"Template Structure"}),Object(o.jsx)("text",{children:" - Start by uploading your excel file template"})]}),Object(o.jsx)("div",{className:"mb-6 w-full text-left",children:Object(o.jsx)(L,{onDrop:d})}),Object(o.jsx)("div",{className:"w-full max-w-lg",children:Object(o.jsxs)("div",{className:"flex flex-wrap -mx-3 mb-6",children:[Object(o.jsx)(P,{label:"*Name:",id:"name",onChange:function(e){var t,a=null===(t=e.target.value)||void 0===t?void 0:t.replace(" ","");r({type:"UPDATE",payload:Object(l.a)(Object(l.a)({},n),{},{name:a})}),a&&a.length>=6&&c(a)},value:n.name,maxLength:20,placeholder:"Enter template name without spaces (min 6 , max 20 char)",disabled:i}),s&&(null===(t=n.name)||void 0===t?void 0:t.length)>=6&&Object(o.jsxs)("span",{className:"flex flex-wrap m-1 text-red-400 text-sm",children:["Name ",n.name," already exist."]})]})}),Object(o.jsx)("div",{className:"w-full max-w-lg",children:Object(o.jsx)("div",{className:"flex flex-wrap -mx-3 mb-6",children:Object(o.jsx)(P,{label:"*Description:",id:"description",onChange:function(e){r({type:"UPDATE",payload:Object(l.a)(Object(l.a)({},n),{},{description:e.target.value})})},value:n.description,maxLength:300,placeholder:"Enter description (max 300 char)"})})}),Object(o.jsx)("div",{className:"w-full max-w-lg",children:Object(o.jsx)("div",{className:"flex flex-wrap -mx-3 mb-6",children:Object(o.jsx)(p,{disabled:!n.name||!n.description||n.name.length<6||s,onClick:function(){return a()},children:"Save"})})})]})},J=n(50),A=n(23),M=function(e){var t=e.template,n=void 0===t?{}:t,a=Object(r.useRef)(null);return Object(r.useEffect)((function(){a.current.innerHTML=J.a.toHtml(n)})),Object(o.jsxs)("div",{className:"flex-grow w-full text-left p-4 border-l-2 border-solid",children:[Object(o.jsx)("text",{className:"text-lg font-bold",children:"Preview"}),Object(o.jsx)("text",{children:" - This is a preview of your report"}),Object(o.jsx)("div",{className:"p-2",children:Object(o.jsx)(A.CopyToClipboard,{className:"bg-green-500 hover:cursor-pointer p-2 rounded-md",text:JSON.stringify(n),onCopy:function(){return alert("Copy to Clipboard was successful.")},children:Object(o.jsx)("span",{children:"Copy JSON to Clipboard"})})}),Object(o.jsx)("div",{className:"border-gray-400 border-solid",children:Object(o.jsx)("div",{className:"w-full text-left mt-2",children:Object(o.jsx)("pre",{className:"relative rounded-xl overflow-auto p-4",ref:a})})})]})},$="xlsx",B=n(24),H=n.n(B),I=function(){return Object(o.jsx)("div",{className:"relative flex py-5 items-center m-3",children:Object(o.jsx)("div",{className:"flex-grow border-t border-gray-200"})})},_={name:null,description:null,format:$,sheets:[],createdBy:"Jed Dayo"},G=function(e){return Object(l.a)({},e)},X="RESET",V=function(e,t){switch(t.type){case"UPDATE":return Object(l.a)(Object(l.a)({},e),t.payload);case"UPDATE_ROW":var n=t.payload,r=n.index,a=n.key,c=n.value,s=n.parent,i=Object(x.a)(e[s]);i[r][a]=c;var o=Object(l.a)({},e);return o[s]=i,Object(l.a)(Object(l.a)({},e),o);case"ADD_ROW":var d=t.payload,u=d.parent,j=d.defaultRow,b=Object(l.a)({},e);return b[u]=[].concat(Object(x.a)(e[u]),[j]),Object(l.a)({},b);case"DELETE_ROW":var p=t.payload,h=p.index,m=p.parent,f=Object(x.a)(e[m]),O=Object(l.a)({},e);return f.splice(h,1),O[m]=Object(x.a)(f),Object(l.a)({},O);case"MOVE_COLUMN_DOWN":var v=t.payload,g=v.index,w=v.parent,y=Object(x.a)(e[w]);y[g].index=y[g].index+1,y[g+1].index=y[g+1].index-1;var N=y[g];y[g]=y[g+1],y[g+1]=N;var k=Object(l.a)({},e);return k[w]=Object(x.a)(y),Object(l.a)({},k);case"MOVE_COLUMN_UP":var C=t.payload,E=C.index,S=C.parent,T=Object(x.a)(e[S]);T[E].index=T[E].index-1,T[E-1].index=T[E-1].index+1;var D=T[E];T[E]=T[E-1],T[E-1]=D;var P=Object(l.a)({},e);return P[S]=Object(x.a)(T),Object(l.a)({},P);case X:return Object(l.a)({},G(_));case"JSON":return Object(l.a)(Object(l.a)({},e),t.payload);default:throw new Error}},W=function(e){var t=e.back,n=e.existingTemplate,a=e.isUpdate,c=Object(r.useReducer)(V,n||_,G),l=Object(s.a)(c,2),i=l[0],d=l[1],u=Object(r.useState)(!1),j=Object(s.a)(u,2),b=j[0],x=j[1],m=Object(r.useState)(),O=Object(s.a)(m,2),v=O[0],w=O[1],N=function(){var e=Object(h.a)(f.a.mark((function e(t){return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,y(t);case 3:200===e.sent.status?w(!0):w(!1),e.next=10;break;case 7:e.prev=7,e.t0=e.catch(0),w(!1);case 10:case"end":return e.stop()}}),e,null,[[0,7]])})));return function(t){return e.apply(this,arguments)}}(),k=function(){var e=Object(h.a)(f.a.mark((function e(){return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,g(i);case 3:t(),d({type:X}),e.next=11;break;case 7:e.prev=7,e.t0=e.catch(0),console.error(e.t0),alert("Error saving the template. Unable to contact server.");case 11:case"end":return e.stop()}}),e,null,[[0,7]])})));return function(){return e.apply(this,arguments)}}(),E=function(){var e=Object(h.a)(f.a.mark((function e(){var t;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:t=null,C(i.name).then((function(e){return 200===e.status?(t=e.headers.get("content-disposition").split(";")[1].split("=")[1],e.blob()):void 0})).then((function(e){H()(e,t,"application/octet-stream")}));case 2:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}();return Object(o.jsxs)("div",{className:"m-2 w-full h-full",children:[Object(o.jsx)(I,{}),Object(o.jsxs)("div",{className:"w-full h-full border-l border-gray-200 gap-2 flex justify-end",children:[a&&Object(o.jsx)(p,{variant:"tertiary",onClick:E,children:"Download Template"}),Object(o.jsx)(p,{variant:"tertiary",onClick:function(){return x(!b)},className:"mr-3",children:"".concat(b?"Close Preview":"Open Preview")})]}),Object(o.jsx)(I,{}),Object(o.jsxs)("div",{className:"m-2 flex w-full h-full",children:[Object(o.jsx)(R,{template:i,dispatchTemplate:d,save:k,doesNameExist:N,exist:v,isUpdate:a}),b&&Object(o.jsx)(M,{template:i})]})]})},q=n(51),K=n.n(q),Y=n(62),z=["title"],Q=function(e){var t=e.title,n=Object(i.a)(e,z);return Object(o.jsx)("div",Object(l.a)(Object(l.a)({className:"font-sans text-lg text-white bg-blue-400 p-2 flex"},n),{},{children:Object(o.jsx)("h2",{children:t})}))},Z=n(61),ee=function(e){var t=e.setHasError,n=e.setJson,a=e.template,c=Object(r.useState)({test:"excel"}),l=Object(s.a)(c,2),i=l[0],d=l[1],u=function(){var e=Object(h.a)(f.a.mark((function e(){var t;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,T(a.name);case 2:if((t=e.sent)&&0!==t.length){e.next=6;break}return alert("No last sample data used."),e.abrupt("return");case 6:d(JSON.parse(t[0].data)),n(t[0].data);case 8:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}();return Object(o.jsxs)("div",{className:"p-2",children:[Object(o.jsx)("span",{className:"text-lg cursor-pointer underline text-blue-500",onClick:u,children:"use last sample"}),Object(o.jsx)(Z.a,{id:a.name,placeholder:i,height:"550px",width:"700px",onChange:function(e){var r=e.json;e.error&&t(!0),d(JSON.parse(r)),n(r)}})]})},te=function(e){var t=e.template,n=Object(r.useState)(),a=Object(s.a)(n,2),c=a[0],l=a[1],i=Object(r.useState)(!1),d=Object(s.a)(i,2),u=d[0],j=d[1],b=Object(r.useState)(!1),x=Object(s.a)(b,2),m=x[0],O=x[1],v=Object(r.useState)([]),g=Object(s.a)(v,2),w=g[0],y=g[1],N=function(){var e=Object(h.a)(f.a.mark((function e(){var n;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,T(t.name);case 2:n=e.sent,y(n);case 4:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}();Object(r.useEffect)((function(){N()}),[]);var k=function(){var e=Object(h.a)(f.a.mark((function e(){var n,r;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:if(n=prompt("How many reports? enter valid number",1),r=new RegExp("^[0-9]*$"),!(n&&r.test(n)&&n<=100)){e.next=8;break}return e.next=5,S(t.name,c,n);case 5:C(),e.next=9;break;case 8:alert("Not a valid count, max is 100");case 9:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}(),C=function(){O(!1),N()};return Object(o.jsxs)(o.Fragment,{children:[!m&&Object(o.jsxs)("div",{className:"m-2 w-full h-full",children:[Object(o.jsxs)("table",{className:"table-auto w-full text-sm",children:[Object(o.jsx)("thead",{children:Object(o.jsxs)("tr",{className:"border-b dark:border-slate-600 font-medium p-4 pl-8 pt-0 pb-3 text-slate-400 dark:text-slate-200 text-left",children:[Object(o.jsx)("th",{width:"20%",children:"Run Date"}),Object(o.jsx)("th",{width:"15%",children:"Count"}),Object(o.jsx)("th",{width:"10%",children:"Memory Consumption (bytes)"}),Object(o.jsx)("th",{width:"10%",children:"Memory Consumption (mb)"}),Object(o.jsx)("th",{width:"10%",children:"Execution time (ms)"}),Object(o.jsx)("th",{width:"20%"})]})}),Object(o.jsx)("tbody",{className:"bg-white dark:bg-slate-800",children:w&&w.map((function(e,t){return Object(o.jsxs)("tr",{className:"text-left",children:[Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:e.executedDateTime}),Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:e.reportCount}),Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:e.memoryInBytes}),Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:e.memoryInMB}),Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:e.elapsedTimeInMS}),Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:Object(o.jsx)("div",{className:"flex gap-2 space-x-8",children:Object(o.jsx)(A.CopyToClipboard,{className:"bg-green-500 hover:cursor-pointer p-2 rounded-md",text:e.data,onCopy:function(){return alert("Copy to Clipboard was successful.")},children:Object(o.jsx)("span",{children:"Copy test data"})})})})]},"row-".concat(t))}))})]}),Object(o.jsx)("div",{className:"p-2 flex gap-2",children:Object(o.jsx)(p,{variant:"tertiary",onClick:function(){return O(!0)},children:"Setup Test Run"})})]}),m&&Object(o.jsxs)(o.Fragment,{children:[Object(o.jsx)("div",{className:"p-2",children:Object(o.jsx)("div",{className:"p-2",children:Object(o.jsx)(ee,{setHasError:j,setJson:l,template:t})})}),Object(o.jsxs)("div",{className:"p-2 flex gap-2",children:[Object(o.jsx)(p,{variant:"tertiary",onClick:k,disabled:u,children:"Run Test"}),Object(o.jsx)(p,{variant:"tertiary",onClick:C,children:"Show History"})]})]})]})},ne=function(e){var t=e.template,n=Object(r.useState)(),a=Object(s.a)(n,2),c=a[0],l=a[1],i=Object(r.useState)(!1),d=Object(s.a)(i,2),u=d[0],j=d[1],b=function(){var e=Object(h.a)(f.a.mark((function e(){var n;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:n=null,E(t.name,c).then((function(e){return 200===e.status?(n=e.headers.get("content-disposition").split(";")[1].split("=")[1],e.blob()):void 0})).then((function(e){H()(e,n,"application/octet-stream")}));case 2:case"end":return e.stop()}}),e)})));return function(){return e.apply(this,arguments)}}();return Object(o.jsxs)(o.Fragment,{children:[Object(o.jsx)("div",{className:"p-2",children:Object(o.jsx)("div",{className:"p-2",children:Object(o.jsx)(ee,{setHasError:j,setJson:l,template:t})})}),Object(o.jsx)("div",{className:"p-2 flex gap-2",children:Object(o.jsx)(p,{variant:"tertiary",onClick:b,disabled:u,children:"Generate Report"})})]})},re={content:{top:"50%",left:"50%",right:"auto",bottom:"auto",marginRight:"-50%",transform:"translate(-50%, -50%)",width:"800px"}},ae=function(e){var t=e.template,n=e.isOpen,a=e.customStyles,c=void 0===a?re:a,l=e.onClose,i=Object(r.useState)(!1),d=Object(s.a)(i,2),j=d[0],b=d[1];return Object(o.jsx)("div",{className:"w-full",children:Object(o.jsxs)(K.a,{isOpen:n,onRequestClose:l,style:c,children:[Object(o.jsx)("button",{onClick:l,className:"absolute top-0 right-0 text-xl",children:Object(o.jsx)(Y.a,{})}),Object(o.jsx)(Q,{title:"Test Your Template - ".concat(t.name)}),Object(o.jsx)("div",{className:"w-full",children:Object(o.jsx)(u,{children:Object(o.jsx)(p,{onClick:function(){return b(!j)},variant:"error",children:"".concat(j?"<< Back to Generator":"Show Performance >>")})})}),Object(o.jsx)(I,{}),j&&Object(o.jsx)(te,{template:t}),!j&&Object(o.jsx)(ne,{template:t})]})})},ce=function(e){var t=e.handleUpdate,n=Object(r.useState)([]),a=Object(s.a)(n,2),c=a[0],l=a[1],i=Object(r.useState)(),d=Object(s.a)(i,2),u=d[0],j=d[1],b=function(){var e=Object(h.a)(f.a.mark((function e(){var t;return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,w();case 3:t=e.sent,l(t),e.next=11;break;case 7:e.prev=7,e.t0=e.catch(0),console.error(e.t0),alert("Error fetching templates. Unable to contact server.");case 11:case"end":return e.stop()}}),e,null,[[0,7]])})));return function(){return e.apply(this,arguments)}}();Object(r.useEffect)((function(){b()}),[]);var x=function(){var e=Object(h.a)(f.a.mark((function e(t){return f.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,k(t);case 3:alert("Delete was successful of template - ".concat(t,".")),b(),e.next=11;break;case 7:e.prev=7,e.t0=e.catch(0),console.error(e.t0),alert("Error fetching templates. Unable to contact server.");case 11:case"end":return e.stop()}}),e,null,[[0,7]])})));return function(t){return e.apply(this,arguments)}}();return Object(o.jsxs)("div",{className:"m-2 w-full h-full",children:[Object(o.jsxs)("table",{className:"table-auto w-full text-sm",children:[Object(o.jsx)("thead",{children:Object(o.jsxs)("tr",{className:"border-b dark:border-slate-600 font-medium p-4 pl-8 pt-0 pb-3 text-slate-400 dark:text-slate-200 text-left",children:[Object(o.jsx)("th",{width:"20%",children:"Name"}),Object(o.jsx)("th",{width:"15%",children:"Format"}),Object(o.jsx)("th",{width:"20%",children:"Description"}),Object(o.jsx)("th",{width:"10%",children:"Action"})]})}),Object(o.jsx)("tbody",{className:"bg-white dark:bg-slate-800",children:c&&c.map((function(e,n){return Object(o.jsxs)("tr",{className:"text-left",children:[Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:e.name}),Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:e.format}),Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:e.description}),Object(o.jsx)("td",{className:"border-b border-slate-100 dark:border-slate-700 p-2",children:Object(o.jsxs)("div",{className:"flex gap-2 space-x-8",children:[Object(o.jsx)(p,{variant:"secondary",onClick:function(){return t(e)},children:"Update"}),Object(o.jsx)(p,{variant:"error",onClick:function(){return x(e.name)},children:"Delete"}),Object(o.jsx)(p,{variant:"tertiary",onClick:function(){return function(e){j(e)}(e)},children:"Test"})]})})]},"row-".concat(n))}))})]}),u&&Object(o.jsx)(ae,{template:u,isOpen:u,onClose:function(){return j(null)}})]})},se=function(){var e=Object(r.useState)(),t=Object(s.a)(e,2),n=t[0],a=t[1],c=Object(r.useState)(),l=Object(s.a)(c,2),i=l[0],d=l[1],j=Object(r.useState)(),b=Object(s.a)(j,2),h=b[0],x=b[1];return Object(o.jsxs)("div",{children:[Object(o.jsx)("div",{className:"w-full",children:Object(o.jsx)(u,{children:Object(o.jsx)(p,{onClick:function(){n?a(!1):(a(!0),x(!1),d(null))},variant:"secondary",children:"".concat(n?"Back to Dashboard":"Add Template")})})}),n&&Object(o.jsx)(W,{back:function(){return a(!1)},existingTemplate:i,isUpdate:h}),!n&&Object(o.jsx)(ce,{handleUpdate:function(e){d(e),a(!0),x(!0)}})]})},le=n(4),ie=n.p+"static/media/logo.dd0d67be.png",oe=function(e){var t=Object.assign({},e),n=Object(le.f)();return Object(o.jsxs)("div",Object(l.a)(Object(l.a)({className:"w-full flex bg-blue-500 text-white h-20"},t),{},{children:[Object(o.jsx)("img",{alt:"logo",src:ie,className:"m-3 cursor-pointer",onClick:function(){return n("/")}}),Object(o.jsx)("div",{className:"m-auto cursor-pointer",onClick:function(){return n("/")},children:"Template Report Maintenance"}),Object(o.jsx)("div",{className:"mt-7 mr-8 text-lg text-black underline cursor-pointer",onClick:function(){return n("/documentation")},children:"Documentation"})]}))},de=function(e){var t=e.children;return Object(o.jsx)("div",{className:"w-full h-full",children:t})},ue=function(e){var t=e.doc;return Object(o.jsx)("div",{className:"mt-2 ml-10 mr-10",children:t.content.map((function(e){return Object(o.jsxs)("div",{children:[Object(o.jsx)("h2",{className:"text-lg text-left font-semibold mt-2",children:e.header}),Object(o.jsx)(I,{}),Object(o.jsx)("div",{className:"text-left",children:e.body})]},t.name)}))})},je={name:"expressiondoc",content:[{header:"Expressions - ${}",body:Object(o.jsxs)("div",{children:[Object(o.jsx)("p",{children:"- Expressions are defined string commands in the template that the rendering engine understands. The rendering engine will evaluate and render the cell based on the expression used."}),Object(o.jsx)("p",{className:"mb-2",children:"- Each expression has a different purpose, please read docs below for each."}),Object(o.jsxs)("div",{children:[Object(o.jsx)("h2",{className:"text-lg text-left font-semibold mt-2 underline",children:"Object Expressions - ${Client Details:clientCode1}"}),Object(o.jsx)("p",{children:"- maps to json data"}),Object(o.jsx)("p",{children:"- if returns a single result , will render that into the cell"}),Object(o.jsx)("p",{children:"- if returns a list , will render as comma delimited string"})]}),Object(o.jsxs)("div",{children:[Object(o.jsx)("h2",{className:"text-lg text-left font-semibold mt-2 underline",children:"Row Function Expression - ${row(assetCode#Asset NAV Details:assetCode)}"}),Object(o.jsx)("p",{children:"- maps to json data with key identified in string# , in this example, the key to get is assetCode."}),Object(o.jsx)("p",{children:"- read through the whole row and renders each cell"}),Object(o.jsx)("p",{children:"- if cell is not rendered due to dependencies to other cells, it will be added to the delayed rendered list, which will be processed later once its dependencies are resolved."})]}),Object(o.jsxs)("div",{children:[Object(o.jsx)("h2",{className:"text-lg text-left font-semibold mt-2 underline",children:"Divide Function Expression - ${divide(J9,J12)}"}),Object(o.jsx)("p",{children:"- accepts 2 parameters, dividend and divisor, it can be an Excel cell address or x,y axis 0 index."}),Object(o.jsx)("p",{children:"- renders the cell with the Excel division formula"})]}),Object(o.jsxs)("div",{children:[Object(o.jsx)("h2",{className:"text-lg text-left font-semibold mt-2 underline",children:"Total Column Function Expression - ${totalCol(L9)}"}),Object(o.jsx)("p",{children:"- accepts 1 parameter, it can be an Excel cell address or x,y axis 0 index."}),Object(o.jsx)("p",{children:"- renders the cell with the Excel sum formula which targets a specific cell"}),Object(o.jsx)("p",{children:"- automatically adds the SUM range for dynamically created rows for the target cell"})]}),Object(o.jsxs)("div",{children:[Object(o.jsx)("h2",{className:"text-lg text-left font-semibold mt-2 underline",children:"Sum Function Expression - ${sum(D17,D18)}"}),Object(o.jsx)("p",{children:"- accepts 2 parameters, it can be an Excel cell address or x,y axis 0 index."}),Object(o.jsx)("p",{children:"- renders the cell with the Excel sum formula which targets 2 specific cell"})]}),Object(o.jsxs)("div",{children:[Object(o.jsx)("h2",{className:"text-lg text-left font-semibold mt-2 underline",children:"Reference Function Expression - ${ref(D17)}"}),Object(o.jsx)("p",{children:"- accepts 1 parameter, it can be an Excel cell address or x,y axis 0 index."}),Object(o.jsx)("p",{children:"- renders the cell with the Excel reference formula (=D17) which just refers to a specific cell"})]}),Object(o.jsxs)("div",{children:[Object(o.jsx)("h2",{className:"text-lg text-left font-semibold mt-2 underline",children:"Total Function Expression - ${total(NAV Details:clientContribution)}"}),Object(o.jsx)("p",{children:"- accepts json data mapping."}),Object(o.jsx)("p",{children:"- renders the cell with the total sum of the json data list results"})]}),Object(o.jsxs)("div",{children:[Object(o.jsx)("h2",{className:"text-lg text-left font-semibold mt-2 underline",children:"Total Negative Function Expression - ${totalNegative(NAV Details:netIncreaseDecrease)}"}),Object(o.jsx)("p",{children:"- accepts json data mapping."}),Object(o.jsx)("p",{children:"- renders the cell with the total negative sum of the json data list results"})]})]})}]},be=n.p+"static/media/add-template.85c88d07.gif",pe=n.p+"static/media/design-sample-template.dd2b380f.png",he=n.p+"static/media/save-template.bc659f58.gif",xe={name:"templatedoc",content:[{header:"Adding your first template",body:Object(o.jsxs)("div",{children:[Object(o.jsx)("p",{children:"- Templates are created in MS Excel and as of now we only support the below versions."}),Object(o.jsx)("p",{className:"m-4",children:Object(o.jsxs)("ul",{className:"list-disc ",children:[Object(o.jsx)("li",{children:"Microsoft Excel 2004 (XLS)"}),Object(o.jsx)("li",{children:"Microsoft Excel 2007 (XLSX)"})]})}),Object(o.jsx)("p",{children:"- In your Excel file you may do your usual designs, such as coloring, fonts, margins, merge cells and other more."}),Object(o.jsx)("img",{src:pe,alt:"design-template"}),Object(o.jsx)("p",{className:"mt-4",children:"- Once you are done, you can upload the excel file into the template manager web application. Click on 'Add Template' and upload your excel template."}),Object(o.jsx)("img",{src:be,alt:"add-button"}),Object(o.jsx)("p",{className:"mt-4",children:"- Template names are unique, enter a unique name for your template and add a description."}),Object(o.jsx)("p",{children:"- The template manager will convert the excel file into a json formatted template which will be used by the report generator when generating the reports. You may click on Preview to display the json generated."}),Object(o.jsx)("p",{children:"- Once you are done, you can save the template by clicking on the Save button."}),Object(o.jsx)("img",{src:he,alt:"save-template"})]})}]},me=function(e){var t=e.setDoc;return Object(o.jsxs)("div",{className:"w-full h-full text-left bg-blue-300 ml-2",children:[Object(o.jsx)("h3",{children:Object(o.jsx)("strong",{children:"Getting Started"})}),Object(o.jsx)("h4",{className:"ml-6 underline cursor-pointer",onClick:function(){return t(xe)},children:"Templates"}),Object(o.jsx)("h4",{className:"ml-6 underline cursor-pointer",onClick:function(){return t(je)},children:"Expressions"})]})},fe=function(){var e=Object(r.useState)(xe),t=Object(s.a)(e,2),n=t[0],a=t[1];return Object(o.jsx)("div",{className:"w-full",children:Object(o.jsxs)("div",{className:"flex w-full",children:[Object(o.jsx)("div",{className:"w-60",children:Object(o.jsx)(me,{setDoc:a})}),Object(o.jsx)("div",{className:"w-full",children:Object(o.jsx)(de,{children:Object(o.jsx)(ue,{doc:n})})})]})})},Oe=function(){var e=Object(le.f)();return Object(r.useEffect)((function(){e("/")}),[]),Object(o.jsxs)("div",{className:"App",children:[Object(o.jsx)(oe,{}),Object(o.jsx)(I,{}),Object(o.jsx)(de,{children:Object(o.jsxs)(le.c,{children:[Object(o.jsx)(le.a,{exact:!0,path:"/",element:Object(o.jsx)(se,{})}),Object(o.jsx)(le.a,{exact:!0,path:"/documentation",element:Object(o.jsx)(fe,{})})]})})]})},ve=function(e){e&&e instanceof Function&&n.e(3).then(n.bind(null,91)).then((function(t){var n=t.getCLS,r=t.getFID,a=t.getFCP,c=t.getLCP,s=t.getTTFB;n(e),r(e),a(e),c(e),s(e)}))},ge=n(21);n(40);c.a.render(Object(o.jsx)(ge.a,{children:Object(o.jsx)(Oe,{})}),document.getElementById("root")),ve()}},[[90,1,2]]]);
//# sourceMappingURL=main.9e51a0eb.chunk.js.map