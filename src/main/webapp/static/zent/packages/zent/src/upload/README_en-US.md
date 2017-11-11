---
title: Upload
path: component/upload
group: Data Entry
---

# Upload

Image uploader.

### API

| Property | Description | Type | Default | Required |
|------|------|------|--------|--------|
| localOnly | Allow local images only | boolean | `false` | No |
| tips | Hint text | string | `''` | No |
| maxSize | Image size limit in bytes | number | `1024 * 1024` | No |
| maxAmount | Limit number of images, 0 means no limit | number | `0` | No |
| accept | Allowed file types | string | `'image/gif, image/jpeg, image/png'` | No |
| silent | No notification about sucesss/failure when set to true | boolean | `false` | No |
| triggerInline | Make trigger node's display inline | boolean | `false` | No |
| onFetch | Callback to fetch remote image | function | `noop` | No |
| onUpload | Callback to upload local image | function | `noop` | No |
| onProgress | Progress callback when uploading local image  | function | `noop` | No |
| filterFiles | Filter local files, supports Promise as return value | function | `noop` | No |
| auto | Open upload dialog automatically | boolean | `false` | No |
| triggerClassName | Custom trigger class name | string | `'zent-upload-trigger'` | No |
| prefix | Custom prefix | string | `'zent'` | No |
| withoutPopup | Don't render inside a popup | boolean | `false` | No |
