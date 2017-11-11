---
order: 1
zh-CN:
	title: 基础用法
	tip: "建议尺寸：640 x 640 像素"
en-US:
	title: Basics
	tip: "Recommended image size: 640px x 640px"
---

```jsx
import { Upload } from 'zent';

class Simple extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            imageList: []
        };
        this.updateLocalImage = this.updateLocalImage.bind(this);
    }

    renderTrigger() {
        return <span></span>;
    }

    fetchNetworkImage(data) {
        return new Promise(resolve => {
            console.log(data);
            resolve(data);
        });
    }

    updateLocalImage(data) {
        return new Promise(resolve => {
            this.setState({
                imageList: data
            });
            resolve(data);
        })
    }

    render() {
        return (
            <div>
                <Upload
                    maxSize={8 * 1000 * 1000}
                    triggerInline
                    tips="{i18n.tip}"
                    onFetch={this.fetchNetworkImage}
                    onUpload={this.updateLocalImage}
                />
                {
                    this.state.imageList.map((item, index) => {
                        return <img width="80" height="80" key={index} src={item.src} style={{marginLeft: '10px'}} />
                    })
                }
            </div>
        );
    }
}

ReactDOM.render(
    <Simple />
    , mountNode
);
```
