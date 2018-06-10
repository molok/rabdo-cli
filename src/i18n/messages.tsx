import * as React from 'react';
import {Component} from "react";

export interface TranslatedMessage {
    it: string,
    en: string
}
const messages = {
    welcome: {
        it: "Benvenuto",
        en: "Welcome",
    },
    searching: {
        it: "Ricerca della combinazione migliore...",
        en: "Searching for the best combination...",
    },
    find: {
        it: "Cerca la combinazione migliore",
        en: "Find the best combination",
    },
};

interface FlagProps {
    selected: boolean
    onClick: () => void
}

export class FlagIta extends Component<FlagProps, {}> {
    render() {
        let style = this.props.selected ? {borderBottom: "1px solid #999"} : {};
        return (
        <svg version="1.1" id="Layer_1"  x="0px" y="0px" viewBox="0 0 512 512"
             width="24" height="24" style={style} onClick={this.props.onClick}
        >
        <path fill="#73AF00" d="M170.667,423.724H8.828c-4.875,0-8.828-3.953-8.828-8.828V97.103c0-4.875,3.953-8.828,8.828-8.828
        h161.839V423.724z"/>
        <rect x="170.67" y="88.276" fill="#F5F5F5" width="170.67" height="335.448"/>
        <path fill="#FF4B55" d="M503.172,423.724H341.333V88.276h161.839c4.875,0,8.828,3.953,8.828,8.828v317.793
        C512,419.772,508.047,423.724,503.172,423.724z"/>
        </svg>
        )
    }
}

export class FlagEn extends Component<FlagProps, {}> {
    render() {
        let style = this.props.selected ? {borderBottom: "1px solid #999"} : {};
        return (
<svg version="1.1" id="Layer_1"  x="0px" y="0px"
	 viewBox="0 0 512.001 512.001"
     width="24" height="24" style={style} onClick={this.props.onClick}
>
<path fill="#41479B" d="M503.172,423.725H8.828c-4.875,0-8.828-3.953-8.828-8.828V97.104c0-4.875,3.953-8.828,8.828-8.828
	h494.345c4.875,0,8.828,3.953,8.828,8.828v317.793C512,419.772,508.047,423.725,503.172,423.725z"/>
<path fill="#F5F5F5" d="M512,97.104c0-4.875-3.953-8.828-8.828-8.828h-39.495l-163.54,107.147V88.276h-88.276v107.147
	L48.322,88.276H8.828C3.953,88.276,0,92.229,0,97.104v22.831l140.309,91.927H0v88.276h140.309L0,392.066v22.831
	c0,4.875,3.953,8.828,8.828,8.828h39.495l163.54-107.147v107.147h88.276V316.578l163.54,107.147h39.495
	c4.875,0,8.828-3.953,8.828-8.828v-22.831l-140.309-91.927H512v-88.276H371.691L512,119.935V97.104z"/>
<g>
	<polygon fill="#FF4B55" points="512,229.518 282.483,229.518 282.483,88.276 229.517,88.276 229.517,229.518 0,229.518
		0,282.483 229.517,282.483 229.517,423.725 282.483,423.725 282.483,282.483 512,282.483 	"/>
	<path fill="#FF4B55" d="M178.948,300.138L0.25,416.135c0.625,4.263,4.14,7.59,8.577,7.59h12.159l190.39-123.586H178.948z"/>
	<path fill="#FF4B55" d="M346.388,300.138H313.96l190.113,123.404c4.431-0.472,7.928-4.09,7.928-8.646v-7.258
		L346.388,300.138z"/>
	<path fill="#FF4B55" d="M0,106.849l161.779,105.014h32.428L5.143,89.137C2.123,90.54,0,93.555,0,97.104V106.849z"/>
	<path fill="#FF4B55" d="M332.566,211.863L511.693,95.586c-0.744-4.122-4.184-7.309-8.521-7.309h-12.647L300.138,211.863 H332.566z"/>
</g>
</svg>
    )
    }
}


export default messages