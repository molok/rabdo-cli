import * as React from 'react';
import {Component} from 'react';
import { FormGroup, Row } from "react-bootstrap";
import MineralInput from "./MineralInput";
import {MineralContent} from "../model/index";

interface MineralFormProps {
    water: MineralContent
    attrChanged: (attrName: string, value: number) => void
    editable: boolean
}

class MineralForm extends Component<MineralFormProps, {}> {
    render() {
        return (
            <>
                <FormGroup>
                    <Row>
                        <MineralInput label="Calcio" symbol="Ca" value={this.props.water.ca}
                                      onChange={this.props.attrChanged.bind(this.props, "ca")}
                                      editable={this.props.editable}/>
                        <MineralInput label="Magnesio" symbol="Mg" value={this.props.water.mg}
                                      onChange={this.props.attrChanged.bind(this.props, "mg")}
                                      editable={this.props.editable}/>
                        <MineralInput label="Sodio" symbol="Na" value={this.props.water.na}
                                      onChange={this.props.attrChanged.bind(this.props, "na")}
                                      editable={this.props.editable}/>
                    </Row>
                </FormGroup>
                <FormGroup>
                    <Row>
                        <MineralInput label="solfati" symbol="SO4" value={this.props.water.so4}
                                      onChange={this.props.attrChanged.bind(this.props, "so4")}
                                      editable={this.props.editable}/>
                        <MineralInput label="cloruro" symbol="Cl" value={this.props.water.cl}
                                      onChange={this.props.attrChanged.bind(this.props, "cl")}
                                      editable={this.props.editable}/>
                        <MineralInput label="bicarbonati" symbol="HCO3" value={this.props.water.hco3}
                                      onChange={this.props.attrChanged.bind(this.props, "hco3")}
                                      editable={this.props.editable}/>
                    </Row>
                </FormGroup>
            </>
        );
    }
}

export default MineralForm;